package batch_build.mojo.tasks;

import java.util.Set;

public class PigTask extends Task {

	public PigTask(String name, Set<String> sourceResources,
			Set<String> sinkResources, String source) {
		super(name, sourceResources, sinkResources, source);
	}
	
	public String toString(){
		return "pig:" + name + " sources:" + sourceResources + " sinks:" + sinkResources;
	}
	
}
