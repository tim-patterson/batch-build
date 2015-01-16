package batch_build.mojo.tasks;

import java.util.Collections;
import java.util.Set;

public abstract class Task {
	public final String name;
	public final Set<String> sourceResources;
	public final Set<String> sinkResources;
	
	public Task(String name, Set<String> sourceResources,
			Set<String> sinkResources) {
		this.name = name;
		this.sourceResources = Collections.unmodifiableSet(sourceResources);
		this.sinkResources = Collections.unmodifiableSet(sinkResources);
	}
	
	public String toString(){
		return name;
	}
}
