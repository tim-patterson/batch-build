package batch_build.common.model.tasks;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(property="name", generator=ObjectIdGenerators.PropertyGenerator.class)
public class LinkedTask {
	private Task task;
	public Set<LinkedTask> parents = new HashSet<>();
	
	private LinkedTask(){}
	
	public LinkedTask(Task task){
		this.task = task;
	}
	
	/**
	 * Returns true if the task is this or any of its parent
	 * tasks
	 * @param task
	 * @return
	 */
	public boolean isDependentOn(LinkedTask task){
		if (this.equals(task)){
			return true;
		}
		for (LinkedTask parentTask : parents){
			if (parentTask.isDependentOn(task)){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		return task.toString();
	}
	
	public Set<LinkedTask> getParents(){
		return parents;
	}
	
	public Task getTask(){
		return task;
	}
	
	public String getName(){
		return task.getName();
	}
	
	public void setName(String name){
		// Just here to keep jackson happy, but the task itself should already contain its name
	}
}
