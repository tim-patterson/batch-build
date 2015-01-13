package batch_build.mojo.resources;


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
