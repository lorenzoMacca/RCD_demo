package core.data;

public class RCDData {
	
	private static RCDData dataInstance = null;
	private Gear gear;
	
	private RCDData() {
		this.gear = Gear.N;
	}
	
	public RCDData getInstance(){
		if( dataInstance == null ){
			dataInstance = new RCDData();
		}
		return dataInstance;
	}

	/**
	 * @return the gear
	 */
	public synchronized Gear getGear() {
		return gear;
	}

	/**
	 * @param gear the gear to set
	 */
	public synchronized void setGear(Gear gear) {
		this.gear = gear;
	}

}
