package batch_build.mojo.tasks;

import java.util.HashSet;
import java.util.Set;

public class LinkedTask {
	public final Task task;
	public Set<LinkedTask> parents = new HashSet<>();
	
	public LinkedTask(Task task){
		this.task = task;
	}
}
