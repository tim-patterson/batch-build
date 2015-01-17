package batch_build.mojo.tasks;

import java.util.Set;

public class HiveTask extends Task {

	public HiveTask(String name, Set<String> sourceResources,
			Set<String> sinkResources, String source) {
		super(name, sourceResources, sinkResources, source);
	}
	
	public String toString(){
		return "hive:" + name + " sources:" + sourceResources + " sinks:" + sinkResources;
	}
	
}
