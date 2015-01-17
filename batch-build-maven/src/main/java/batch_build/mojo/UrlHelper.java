package batch_build.mojo;

import batch_build.common.model.resources.HCatResource;
import batch_build.common.model.resources.Resource;


public class UrlHelper {
	
	public static final UrlHelper INSTANCE = new UrlHelper();
	
	public String urlFromResource(Resource resource){
		if (resource instanceof HCatResource){
			HCatResource table = (HCatResource) resource;
			return "tables/" + table.getDbName() + "/" + table.getTableName() + ".html";
		}
		return "";
	}
	
	public String urlFromTaskName(String taskName){
		return "tasks/" + taskName + ".html";
	}

}
