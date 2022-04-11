package P5a;

public class ElectricCharger  {
	private boolean connected;
	public static int POWER = 25000;

	public ElectricCharger(boolean connected) {
		this.connected = false;
	}
	
	
	public void connect() {
		connected = true;
		return;
	}

	public void disconnect() {
		connected = false;
		return;
	}
	
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public static int getPOWER() {
		return POWER;
	}

	public static void setPOWER(int pOWER) {
		POWER = pOWER;
	}
	
	
}
