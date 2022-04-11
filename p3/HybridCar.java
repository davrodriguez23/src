package p3;
public class HybridCar {
	private String plate;
	private String manufacturer;
	private int mechanicalPower;
	private int electricPower;
	private float batteryCharge;
	private static String PLATE_FORMAT = "DDDDLLL";
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

		this.plate = plate;
		this.manufacturer = manufacturer;
		this.mechanicalPower = mechanicalPower;
		this.electricPower = electricPower;
		this.batteryCharge = batteryCharge;
	}

	public static boolean isValidPlate(String plate) {

		if (plate.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
			return true;

		} else {
			System.out.println("Incorrect plate value:" + plate + ".Not comply with format " + PLATE_FORMAT);
			return false;
		}
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

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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

	public void increaseBatteryChargeLevel(float increment) {
		float battery = getBatteryCharge();
		battery = battery + increment;
		if (battery > 100f) {
			battery = 100f;
		}
		setBatteryCharge(battery);
		return;
	}

	public String toText() {
		return sigla + ";" + plate + ";" + manufacturer + ";" + mechanicalPower + ";" + electricPower + ";"
				+ batteryCharge;
	}

}
