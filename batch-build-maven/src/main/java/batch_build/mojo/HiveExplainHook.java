package batch_build.mojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.hooks.ReadEntity;
import org.apache.hadoop.hive.ql.hooks.WriteEntity;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.api.StageType;

public class HiveExplainHook implements HiveSemanticAnalyzerHook {
	
	private static Set<String> sinks = new HashSet<>();
	private static Set<String> sources= new HashSet<>();
	
	public static void reset(){
		sinks = new HashSet<>();
		sources = new HashSet<>();
	}
	

	@Override
	public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context,
			ASTNode ast) throws SemanticException {
		return ast;
	}

	@Override
	public void postAnalyze(HiveSemanticAnalyzerHookContext context,
			List<Task<? extends Serializable>> rootTasks)
			throws SemanticException {
		// Allow DDL
		if(rootTasks.get(0).getType() == StageType.DDL){
			return;
		}
		for(ReadEntity readEntity : context.getInputs()){
			sources.add("hcat:" + readEntity.getTable().getDbName() + "." + readEntity.getTable());
		}
		for(WriteEntity writeEntity : context.getOutputs()){
			sinks.add("hcat:" + writeEntity.getTable().getDbName() + "." + writeEntity.getTable());
		}
		
		// Turn Query into NOOP
		rootTasks.clear();
	}

	public static Set<String> getSinks(){
		return sinks;
	}
	
	public static Set<String> getSources(){
		return sources;
	}

}
