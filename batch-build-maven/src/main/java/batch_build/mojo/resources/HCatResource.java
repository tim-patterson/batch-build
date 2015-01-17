package batch_build.mojo.resources;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class HCatResource extends Resource{
	
	public final String dbName;
	public final String tableName;
	public final String description;
	public final String source;
	public final List<HCatColumn> columns;

	
	public HCatResource(String dbName, String tableName, String description, String sourceSql,
			List<HCatColumn> columns) {
		this.dbName = dbName;
		this.tableName = tableName;
		this.description = description;
		this.source = sourceSql;
		this.columns = Collections.unmodifiableList(columns);
	}

	@Override
	public String getUniqueIdentifier() {
		return "hcat:" + dbName + "." + tableName;
	}
	
	
	@Getter
	public static class HCatColumn{
		public final String type;
		public final String name;
		public final String description;
		public final boolean isPartitionKey;
		
		public HCatColumn(String type, String name, String description, boolean isPartitionKey) {
			this.type = type;
			this.name = name;
			this.description = description;
			this.isPartitionKey = isPartitionKey;
		}
	}
}
