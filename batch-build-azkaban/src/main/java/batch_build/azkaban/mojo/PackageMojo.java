package batch_build.azkaban.mojo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;

import batch_build.common.model.BatchModel;
import batch_build.common.model.tasks.HiveTask;
import batch_build.common.model.tasks.LinkedTask;
import batch_build.common.model.tasks.PigTask;

@Mojo(requiresProject = true, name = "package", defaultPhase = LifecyclePhase.PACKAGE)
public class PackageMojo extends AbstractMojo {
	
	@Parameter(defaultValue = "${project.build.directory}/model.json", readonly = true, required = true)
	private File modelFile;
	
	@Parameter(defaultValue = "${project.build.directory}/${project.name}-flow-${project.version}.zip", readonly = true, required = true)
	private File flowFile;
	
	@Parameter(defaultValue = "${project}", required=true)
	private MavenProject project;
	
	private BatchModel model;
	
	private ZipOutputStream out;

	public void execute() throws MojoExecutionException {
		try {
			model = BatchModel.read(modelFile);
			out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(flowFile)));
			File tasksDir = new File(project.getBuild().getOutputDirectory(), "tasks");
			zipDir(tasksDir, tasksDir.toPath());
			
			Set<String> allTaskNames = new HashSet<>();
			Set<String> nonLeafTaskNames = new HashSet<>();
			
			
			for (LinkedTask task : model.getTasks()){
				String taskName = task.getName();
				allTaskNames.add(taskName);
				out.putNextEntry(new ZipEntry(taskName + ".job"));
				PrintStream ps = new PrintStream(out);
				ps.println("# " + taskName);
				if (task.getTask() instanceof HiveTask){
					ps.println("type=hive");
					ps.println("hive.script=" + taskName);
				} else if (task.getTask() instanceof PigTask){
					ps.println("type=pig");
					ps.println("pig.script=" + taskName);
				} else {
					throw new RuntimeException("Unknown task type " + task.getTask().getClass().getSimpleName());
				}
				if (!task.parents.isEmpty()){
					List<String> parentNames = new ArrayList<>();
					for (LinkedTask parent : task.parents){
						parentNames.add(parent.getName());
						nonLeafTaskNames.add(parent.getName());
					}
					ps.println("dependencies=" + StringUtils.join(parentNames.iterator() , ","));
				}
				ps.flush();
			}
			
			allTaskNames.removeAll(nonLeafTaskNames);
			// allTaskNames now only contains the leaf node names
			out.putNextEntry(new ZipEntry("FLOW.job"));
			PrintStream ps = new PrintStream(out);
			ps.println("type=noop");
			ps.println("dependencies=" + StringUtils.join(allTaskNames.iterator() , ","));
			ps.flush();
			
			out.close();
		} catch (Throwable e) {
			throw new MojoExecutionException("Problem running mojo", e);
		}
	}
	
	
	
	private void zipDir(File dir, Path baseDir) throws IOException{
		for (File f : dir.listFiles()){
			if (f.isFile()){
				String relativePath = baseDir.relativize(f.toPath()).toString().replace('\\', '/');
				ZipEntry z = new ZipEntry(relativePath);
				out.putNextEntry(z);
				FileUtils.copyFile(f, out);
			}else {
				zipDir(f, baseDir);
			}
		}
	}
	
}
