package P5a;
public class ElectricCar extends Car{
	private int electricPower;
	private float batteryCharge;
	private static int powerMin = 50;
	private static int powerMax = 800;
	private static float batteryMin = 0.0f;
	private static float batteryMax = 100.0f;
	private static String sigla = "E";
	public ElectricCar() {
		super();
	}

	public ElectricCar(String plate, String manufacturer, int electricPower, float batteryCharge) {
		super(plate, manufacturer);
		this.electricPower = electricPower;
		this.batteryCharge = batteryCharge;
	}

	

	public static boolean isValidPower(int electricPower) {

		if (electricPower > 50 && electricPower < 800) {
			return true;
		} else {
			System.out.println(
					"Incorrect power value:" + electricPower + ". Valid range is " + powerMin + "-" + powerMax);
			return false;
		}
	}

	public static boolean isValidBattery(float batteryCharge) {

		if (batteryCharge > 0.0f && batteryCharge < 100.0f) {
			return true;
		} else {
			System.out.println(
					"Incorrect battery value:" + batteryCharge + ". Valid range is " + batteryMin + "-" + batteryMax);
			return false;
		}
	}

	public int getTotalPower(){
		int power = 0;
		power = power + getPower();
		return power;
	}

	public int getPower() {
		return electricPower;
	}

	public void setPower(int electricPower) {
		this.electricPower = electricPower;
	}

	public float getBatteryCharge() {
		return batteryCharge;
	}

	public void setBatteryCharge(float batteryCharge) {
		this.batteryCharge = batteryCharge;
	}

	public void increaseBatteryChargeLevel(float increment) {
		float battery = getBatteryCharge();
		battery = battery + increment;
		if (battery > 100f) {
			battery = 100f;
		}
		setBatteryCharge(battery);
		return;
	}

	public String toString() {
		return sigla + ";" + super.toString() + ";" + electricPower + ";" + batteryCharge;
	}

}
