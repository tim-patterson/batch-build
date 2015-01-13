package batch_build.mojo;

import java.io.File;

import org.apache.hadoop.hive.cli.CliDriver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.pig.Main;

@Mojo(requiresDirectInvocation=true, name = "pig")
public class PigMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project.build.directory}/tmp", readonly = true, required = true)
	private File tmpDir;

	public void execute() throws MojoExecutionException {
		try {
			CompileMojo.setupHadoop(tmpDir);
			Main.main(new String[]{});
		} catch (Throwable e) {
			throw new MojoExecutionException("Problem running mojo", e);
		}
	}
}
