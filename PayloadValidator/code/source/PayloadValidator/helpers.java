package PayloadValidator;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;
// --- <<IS-END-IMPORTS>> ---

public final class helpers

{
	// ---( internal utility methods )---

	final static helpers _instance = new helpers();

	static helpers _newInstance() { return new helpers(); }

	static helpers _cast(Object o) { return (helpers)o; }

	// ---( server methods )---




	public static final void prepareFunctionHelper (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(prepareFunctionHelper)>> ---
		// @sigtype java 3.5
		// [i] field:0:required functionName
		// [i] record:1:required Params
		// [i] - field:0:required type {"Value","Variable","Regex"}
		// [i] - field:0:required value
		// [o] field:0:required functionDeclaration
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	functionName = IDataUtil.getString( pipelineCursor, "functionName" );
			StringBuffer sb = new StringBuffer();
			sb.append(functionName+"(");
			// Params
			IData[]	Params = IDataUtil.getIDataArray( pipelineCursor, "Params" );
			if ( Params != null)
			{
				for ( int i = 0; i < Params.length; i++ )
				{
					IDataCursor ParamsCursor = Params[i].getCursor();
						String	type = IDataUtil.getString( ParamsCursor, "type" );
						String	value = IDataUtil.getString( ParamsCursor, "value" );
						if(type.equalsIgnoreCase("Variable")){
							
							sb.append("%ifvar "+value+"%'%value "+value+"%'%else%undefined%end%");
							
						}else if(type.equalsIgnoreCase("Regex")){
							sb.append(value);
						}else{
							sb.append("'"+value+"'");
						}
						if(i<Params.length-1){
							sb.append(",");
						}
						
					ParamsCursor.destroy();
				}
			}
		pipelineCursor.destroy();
		
		// pipeline
		sb.append(");");
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "functionDeclaration", sb.toString() );
		pipelineCursor_1.destroy();
		
			
		// --- <<IS-END>> ---

                
	}
}

