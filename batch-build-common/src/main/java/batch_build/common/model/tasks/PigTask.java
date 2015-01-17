package batch_build.common.model.tasks;

import java.util.Set;

public class PigTask extends Task {
	
	private PigTask(){}

	public PigTask(String name, Set<String> sourceResources,
			Set<String> sinkResources, String source) {
		super(name, sourceResources, sinkResources, source);
	}
	
	public String toString(){
		return "pig:" + getName() + " sources:" + getSourceResources() + " sinks:" + getSinkResources();
	}
	
}
