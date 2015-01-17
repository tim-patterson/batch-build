package batch_build.common.model.tasks;

import java.util.Collections;
import java.util.Set;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Getter
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class Task {
	private String name;
	private Set<String> sourceResources;
	private Set<String> sinkResources;
	private String source;
	
	protected Task(){}
	
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
