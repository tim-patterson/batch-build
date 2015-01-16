package batch_build.mojo.tasks;

import java.util.Set;

public class HiveTask extends Task {

	public HiveTask(String name, Set<String> sourceResources,
			Set<String> sinkResources) {
		super(name, sourceResources, sinkResources);
	}
	
	public String toString(){
		return "hive:" + name + " sources:" + sourceResources + " sinks:" + sinkResources;
	}
	
}
