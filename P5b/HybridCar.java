package P5b;
public class HybridCar extends Car {
	private int mechanicalPower;
	private int electricPower;
	private float batteryCharge;
	private static int mechanicalPowerMin = 60;
	private static int mechanicalPowerMax = 500;
	private static int electricalPowerMin = 20;
	private static int electricalPowerMax = 100;
	private static float batteryMin = 0.0f;
	private static float batteryMax = 100.0f;
	private static String sigla = "H";
	public HybridCar() {
		super();
	}

	public HybridCar(String plate, String manufacturer, int mechanicalPower, int electricPower, float batteryCharge) {
		super(plate, manufacturer);
		this.mechanicalPower = mechanicalPower;
		this.electricPower = electricPower;
		this.batteryCharge = batteryCharge;
	}

	public static boolean isValidMechanicalPower(int mechanicalPower) {

		if (mechanicalPower > 60 && mechanicalPower < 500) {
			return true;
		} else {
			System.out.println("Incorrect power value:" + mechanicalPower + ". Valid range is " + mechanicalPowerMin
					+ "-" + mechanicalPowerMax);
			return false;
		}
	}

	public static boolean isValidPower(int electricPower) {

		if (electricPower > 20 && electricPower < 100) {
			return true;
		} else {
			System.out.println("Incorrect power value:" + electricPower + ". Valid range is " + electricalPowerMin + "-"
					+ electricalPowerMax);
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
		power = power + getMechanicalPower() + getElectricPower();
		return power;
	}

	public int getMechanicalPower() {
		return mechanicalPower;
	}

	public void setMechanicalPower(int mechanicalPower) {
		this.mechanicalPower = mechanicalPower;
	}

	public int getElectricPower() {
		return electricPower;
	}

	public void setElectricPower(int electricPower) {
		this.electricPower = electricPower;
	}

	public float getBatteryCharge() {
		return batteryCharge;
	}

	public void setBatteryCharge(float batteryCharge) {
		this.batteryCharge = batteryCharge;
	}

	public void increaseBatteryChargeLevel(float chargeTime) {
		float battery = getBatteryCharge();
		ElectricCharger eC = new ElectricCharger(false);
		int chargerPower = eC.power(); 
		battery = battery + (chargeTime * chargerPower / 15000) * 100;
		if (battery > 100f) {
			battery = 100f;
		}
		setBatteryCharge(battery);
		return;
	}

	public String toString() {
		return sigla + ";" + super.toString() + ";" + mechanicalPower + ";" + electricPower + ";"
				+ batteryCharge;
	}

}
