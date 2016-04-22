package core.data;

public class RCDData {
	
	private static RCDData dataInstance = null;
	private Gear gear;
	private Controller controller;
	
	private RCDData() {
		this.gear = Gear.N;
		this.controller = new Controller();
	}
	
	public static RCDData getInstance(){
		if( dataInstance == null ){
			dataInstance = new RCDData();
		}
		return dataInstance;
	}

	/**
	 * @return the gear
	 */
	public Gear getGear() {
		return gear;
	}

	/**
	 * @param gear the gear to set
	 */
	public void setGear(Gear gear) {
		this.gear = gear;
	}

	/**
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RCDData [gear=" + gear + ", controller=" + controller + "]";
	}

}
