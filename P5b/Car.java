package P5b;

abstract class Car {
	private String plate;
	private String manufacturer;
	private static String PLATE_FORMAT = "DDDDLLL";

	public Car() {

	}

	public Car(String plate, String manufacturer) {
		this.plate = plate;
		this.manufacturer = manufacturer;
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

	public static boolean isValidPlate(String plate) {

		if (plate.toUpperCase().matches("^[0-9]{4}[A-Z]{3}$")) {
			return true;

		} else {
			System.out.println("Incorrect plate value:" + plate + ".Not comply with format " + PLATE_FORMAT);
			return false;
		}
	}

	public abstract int getTotalPower();

	public String toString() {
		return plate + ";" + manufacturer;
	}
}
