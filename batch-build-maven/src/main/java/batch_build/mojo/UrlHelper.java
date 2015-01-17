package batch_build.mojo;

import batch_build.mojo.resources.HCatResource;
import batch_build.mojo.resources.Resource;

public class UrlHelper {
	
	public static final UrlHelper INSTANCE = new UrlHelper();
	
	public String urlFromResource(Resource resource){
		if (resource instanceof HCatResource){
			HCatResource table = (HCatResource) resource;
			return "tables/" + table.dbName + "/" + table.tableName + ".html";
		}
		return "";
	}
	
	public String urlFromTaskName(String taskName){
		return "tasks/" + taskName + ".html";
	}

}
