package batch_build.mojo.tasks;

import java.util.HashSet;
import java.util.Set;

public class LinkedTask {
	public final Task task;
	public Set<LinkedTask> parents = new HashSet<>();
	
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
}
