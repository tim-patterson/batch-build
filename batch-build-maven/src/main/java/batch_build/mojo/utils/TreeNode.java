package batch_build.mojo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode<T> implements Comparable<TreeNode<T>>{
	private List<TreeNode<T>> children = new ArrayList<>();
	//for lookups
	private Map<String, TreeNode<T>> childrenByLabel = new HashMap<>();
	private final String label;
	private T item;
	
	public TreeNode(T item, String label){
		this.item = item;
		this.label = label;
	}
	
	public boolean isLeaf(){
		return children.isEmpty();
	}
	
	public void addChild(TreeNode<T> child){
		children.add(child);
		childrenByLabel.put(child.label, child);
	}
	
	public TreeNode<T> getOrCreateChild(String label){
		TreeNode<T> child =  childrenByLabel.get(label);
		if (child == null){
			child = new TreeNode<>(null, label);
			addChild(child);
		}
		return child;
	}
	
	
	public List<TreeNode<T>> getChildren(){
		return Collections.unmodifiableList(children);
	}
	
	public void sortChildren(boolean recursive){
		Collections.sort(children);
		if (recursive){
			for (TreeNode<T> child : children){
				child.sortChildren(true);
			}
		}
	}
	
	public T getItem(){
		return item;
	}
	
	public void setItem(T item){
		this.item = item;
	}
	
	public String toString(){
		return label;
	}

	@Override
	public int compareTo(TreeNode<T> o) {
		return label.compareTo(o.label);
	}
}
