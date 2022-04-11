package p3;
public class CombustionCar {
	private String plate;
	private String manufacturer;
	private int mechanicalPower;
	private static String PLATE_FORMAT = "DDDDLLL";
	private static int powerMin = 60;
	private static int powerMax = 500;
	private static String sigla = "C";
	public CombustionCar() {
		super();
	}

	public CombustionCar(String plate, String manufacturer, int mechanicalPower) {
		super();
		this.plate = plate;
		this.manufacturer = manufacturer;
		this.mechanicalPower = mechanicalPower;
	}

	public static boolean isValidPlate(String plate) {

		if (plate.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
			return true;

		} else {
			System.out.println("Incorrect plate value:" + plate + ".Not comply with format " + PLATE_FORMAT);
			return false;
		}
	}

	public static boolean isValidPower(int power) {

		if (power > 60 && power < 500) {
			return true;
		} else {
			System.out.println("Incorrect power value:" + power + ". Valid range is " + powerMin + "-" + powerMax);
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

	public String toText() {
		return sigla + ";" + plate + ";" + manufacturer + ";" + mechanicalPower;
	}
}
