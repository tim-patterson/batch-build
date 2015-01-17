package batch_build.mojo.tasks;

import java.util.Collections;
import java.util.Set;

import lombok.Getter;

@Getter
public abstract class Task {
	public final String name;
	public final Set<String> sourceResources;
	public final Set<String> sinkResources;
	public final String source;
	
	public Task(String name, Set<String> sourceResources,
			Set<String> sinkResources, String source) {
		this.name = name;
		this.sourceResources = Collections.unmodifiableSet(sourceResources);
		this.sinkResources = Collections.unmodifiableSet(sinkResources);
		this.source = source;
	}
	
	public String toString(){
		return name;
	}
}
