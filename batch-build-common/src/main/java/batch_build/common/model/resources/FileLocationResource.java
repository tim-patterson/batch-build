package batch_build.common.model.resources;


public class FileLocationResource extends Resource {
	
	public final String path;
	
	public FileLocationResource(String path){
		this.path = path;
	}

	@Override
	public String getUniqueIdentifier() {
		return "file:" + path;
	}

}
