package batch_build.common.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import batch_build.common.model.resources.Resource;
import batch_build.common.model.tasks.LinkedTask;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

@Getter
@Setter
public class BatchModel {
	private Map<String,Resource> resources = new HashMap<>();
	private List<LinkedTask> tasks;
	
	public void writeModel(File modelFile) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper om = new ObjectMapper();
		ObjectWriter writer = om.writer().withDefaultPrettyPrinter().forType(BatchModel.class);
		writer.writeValue(modelFile, this);
	}
	
	public static BatchModel read(File modelFile) throws JsonProcessingException, IOException{
		ObjectMapper om = new ObjectMapper();
		ObjectReader reader = om.reader().forType(BatchModel.class);
		return reader.readValue(modelFile);
	}
}
