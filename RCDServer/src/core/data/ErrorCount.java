package core.data;

import java.util.ArrayList;
import java.util.List;

public class ErrorCount {
	
	private List<String> errorHistory;
	private int currentConnectionErrorCount;
	
	public ErrorCount() {
		this.errorHistory = new ArrayList<String>();
		this.currentConnectionErrorCount = 0;
	}
	
	/**
	 * 
	 * @param errorMessage
	 * @return the number of current error
	 */
	public int addConnectionError(String errorMessage){
		this.errorHistory.add(errorMessage);
		return ++this.currentConnectionErrorCount;
	}
	
	/**
	 * reset the CurrentConnectionErrorCount variable
	 * @return the current value of CurrentConnectionErrorCount before the reset
	 */
	public int resetCurrentConnectionErrorCount(){
		int tmp = this.currentConnectionErrorCount;
		this.currentConnectionErrorCount = 0;
		return tmp;
	}

	/**
	 * @return the errorHistory
	 */
	public List<String> getErrorHistory() {
		return errorHistory;
	}

	/**
	 * @return the currentConnectionError
	 */
	public int getCurrentConnectionError() {
		return currentConnectionErrorCount;
	}
	
	

}
