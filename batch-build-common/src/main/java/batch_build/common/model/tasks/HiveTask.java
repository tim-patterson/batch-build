package batch_build.common.model.tasks;

import java.util.Set;

public class HiveTask extends Task {
	
	private HiveTask(){}

	public HiveTask(String name, Set<String> sourceResources,
			Set<String> sinkResources, String source) {
		super(name, sourceResources, sinkResources, source);
	}
	
	public String toString(){
		return "hive:" + getName() + " sources:" + getSourceResources() + " sinks:" + getSinkResources();
	}
	
}
