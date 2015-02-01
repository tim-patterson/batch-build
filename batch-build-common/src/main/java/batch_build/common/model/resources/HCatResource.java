package batch_build.common.model.resources;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
public class HCatResource extends Resource{
	
	private String dbName;
	private String tableName;
	private String description;
	private String source;
	private List<HCatColumn> columns;
	
	private HCatResource(){};

	public HCatResource(String dbName, String tableName, String description, String source,
			List<HCatColumn> columns) {
		this.dbName = dbName;
		this.tableName = tableName;
		this.description = description;
		this.source = source;
		this.columns = Collections.unmodifiableList(columns);
	}

	@Override
	@JsonIgnore
	public String getUniqueIdentifier() {
		return "hcat:" + dbName + "." + tableName;
	}
	
	
	@Getter
	public static class HCatColumn{
		private String type;
		private String name;
		private String description;
		private boolean partitionKey;
		
		private HCatColumn(){};
		
		public HCatColumn(String type, String name, String description, boolean partitionKey) {
			this.type = type;
			this.name = name;
			this.description = description;
			this.partitionKey = partitionKey;
		}
	}
}
