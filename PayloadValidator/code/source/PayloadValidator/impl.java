package PayloadValidator;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import java.io.StringReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;
// --- <<IS-END-IMPORTS>> ---

public final class impl

{
	// ---( internal utility methods )---

	final static impl _instance = new impl();

	static impl _newInstance() { return new impl(); }

	static impl _cast(Object o) { return (impl)o; }

	// ---( server methods )---




	public static final void executeScript (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(executeScript)>> ---
		// @sigtype java 3.5
		// [i] object:0:required scriptObj
		// [i] field:0:required validatorFunctions
		// [o] field:0:required result
		// [o] record:0:required Error
		// [o] - field:0:required Code
		// [o] - field:0:required Description
		// [o] field:0:required exception
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		jdk.nashorn.api.scripting.ScriptObjectMirror	scriptObj = (ScriptObjectMirror) IDataUtil.get( pipelineCursor, "scriptObj" );
		String	validatorFunctions = IDataUtil.getString( pipelineCursor, "validatorFunctions" );		
				
		String result = "true";
		IDataUtil.put( pipelineCursor, "result", result );
		pipelineCursor.destroy();
		
		
		if(scriptObj!=null){			
			try {
				//reader = new StringReader(script);
				//engine.eval(reader);
				String str = (String) scriptObj.eval(validatorFunctions);				
				IDataCursor pipelineCursor_3 = pipeline.getCursor();				
				if(!str.equals("true")){
					
					result ="false";
					
					// pipeline
					IDataCursor pipelineCursor_1 = pipeline.getCursor();
					IDataUtil.put( pipelineCursor_1, "result", result );
					
					// Error
					IData	Error = IDataFactory.create();
					IDataCursor ErrorCursor = Error.getCursor();
					IDataUtil.put( ErrorCursor, "Code", str.split("~")[0] );
					IDataUtil.put( ErrorCursor, "Description", str.split("~")[1] );
					ErrorCursor.destroy();
					IDataUtil.put( pipelineCursor_1, "Error", Error );							
					pipelineCursor_1.destroy();
										
				}else{
					IDataUtil.put( pipelineCursor_3, "result", str );
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				
				result = "false";
				// pipeline
				IDataCursor pipelineCursor_2 = pipeline.getCursor();
				IDataUtil.put( pipelineCursor_2, "result", result );
				IDataUtil.put( pipelineCursor_2, "exception", e.getStackTrace() );		
				
			}
		}else{
			
			throw new ServiceException("Script is Required, Reload Cache");
		}
		
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void loadCache (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(loadCache)>> ---
		// @sigtype java 3.5
		// [i] field:0:required script
		// [o] object:0:required scriptObj
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	script = IDataUtil.getString( pipelineCursor, "script" );
			
		
		
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		
		
		
		if(script!=null){
			
			StringReader reader = null;
			try {
				
				reader = new StringReader(script);
				
				
		
				
		
				// pipeline
				IDataCursor pipelineCursor_1 = pipeline.getCursor();
				Object scriptObj = new Object();
				IDataUtil.put( pipelineCursor_1, "scriptObj", engine.eval(reader) );
				pipelineCursor_1.destroy();
		
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void validateImpl (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validateImpl)>> ---
		// @sigtype java 3.5
		// [i] field:0:required script
		// [i] field:1:required validatorFunctions
		// [o] field:0:required result
		// [o] record:0:required Error
		// [o] - field:0:required Code
		// [o] - field:0:required Description
		// [o] field:0:required exception
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	script = IDataUtil.getString( pipelineCursor, "script" );
			String[]	validatorFunctions = IDataUtil.getStringArray( pipelineCursor, "validatorFunctions" );
		
		
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		
		String result = "true";
		IDataUtil.put( pipelineCursor, "result", result );
		pipelineCursor.destroy();
		
		
		if(script!=null){
			
			StringReader reader = null;
			try {
				reader = new StringReader(script);
				engine.eval(reader);
				if(validatorFunctions.length>0){
					for (int i = 0; i < validatorFunctions.length; i++) {
						String str = (String) engine.eval(validatorFunctions[i]);
						if(!str.equals("true")){
							
							result ="false";
							
							// pipeline
							IDataCursor pipelineCursor_1 = pipeline.getCursor();
							IDataUtil.put( pipelineCursor_1, "result", result );
							
							// Error
							IData	Error = IDataFactory.create();
							IDataCursor ErrorCursor = Error.getCursor();
							IDataUtil.put( ErrorCursor, "Code", str.split("~")[0] );
							IDataUtil.put( ErrorCursor, "Description", str.split("~")[1] );
							ErrorCursor.destroy();
							IDataUtil.put( pipelineCursor_1, "Error", Error );							
							pipelineCursor_1.destroy();
							break;							
						}
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				
				result = "false";
				// pipeline
				IDataCursor pipelineCursor_2 = pipeline.getCursor();
				IDataUtil.put( pipelineCursor_2, "result", result );
				IDataUtil.put( pipelineCursor_2, "exception", e.getStackTrace() );
				
				
			}finally {
				if(reader!=null){
					reader.close();
				}
				
			}
		}else{
			
			throw new ServiceException("Script is Required");
		}
		
		
			
		// --- <<IS-END>> ---

                
	}
}

