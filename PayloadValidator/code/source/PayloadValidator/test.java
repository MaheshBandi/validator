package PayloadValidator;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.Session;
// --- <<IS-END-IMPORTS>> ---

public final class test

{
	// ---( internal utility methods )---

	final static test _instance = new test();

	static test _newInstance() { return new test(); }

	static test _cast(Object o) { return (test)o; }

	// ---( server methods )---




	public static final void calcDiff (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calcDiff)>> ---
		// @sigtype java 3.5
		// [i] object:0:required startTime
		// [i] object:0:required endTime
		// [o] field:0:required diff
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			long	startTime = (long) IDataUtil.get( pipelineCursor, "startTime" );
			long	endTime = (long) IDataUtil.get( pipelineCursor, "endTime" );
		pipelineCursor.destroy();
		
		double diff = (endTime - startTime ) / 1000000.0;
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "diff", Double.toString(diff) );
		pipelineCursor_1.destroy();
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void doParallelThreadInvoke (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(doParallelThreadInvoke)>> ---
		// @sigtype java 3.5
		// [i] field:0:required count
		// pipeline
				//IDataCursor pipelinecursor = pipeline.getCursor();
				
			
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	count = IDataUtil.getString( pipelineCursor, "count" );
		pipelineCursor.destroy();
		
		// pipeline
		
			
		for (int i = 0; i < Integer.parseInt(count); i++) {
		
			Service.doThreadInvoke("BusinessFieldsValidator.test", "testXML",Service.getSession(),IDataUtil.clone(pipeline));
		}
		
			
		// --- <<IS-END>> ---

                
	}
}

