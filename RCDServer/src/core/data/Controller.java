package core.data;

public class Controller {
	
	private String ip;
	private int port;
	private boolean isActive;
	public static final String NA_STRING = "NA";
	public static final int    NA_INT = -1;
	
	public Controller() {
		this.ip = Controller.NA_STRING;
		this.port = Controller.NA_INT;
		this.isActive = false;
	}
	
	public boolean initializeController(String ip, int port, boolean isActive){
		this.setIp(ip);
		this.setPort(port);
		this.isActive = isActive;
		return true;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Controller [ip=" + ip + ", port=" + port + ", isActive="
				+ isActive + "]";
	}
}
