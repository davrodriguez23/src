package P5b;
public class CombustionCar extends Car{
	private int mechanicalPower;
	private static int powerMin = 60;
	private static int powerMax = 500;
	private static String sigla = "C";
	public CombustionCar() {
		super();
	}

	public CombustionCar(String plate, String manufacturer, int mechanicalPower) {
		super(plate, manufacturer);
		this.mechanicalPower = mechanicalPower;
	}

	public static boolean isValidPower(int power) {

		if (power > 60 && power < 500) {
			return true;
		} else {
			System.out.println("Incorrect power value:" + power + ". Valid range is " + powerMin + "-" + powerMax);
			return false;
		}
	}

	public int getTotalPower(){
		int power = 0;
		power = power + getMechanicalPower();
		return power;
	}



	public int getMechanicalPower() {
		return mechanicalPower;
	}

	public void setMechanicalPower(int mechanicalPower) {
		this.mechanicalPower = mechanicalPower;
	}

	public String toString() {
		return sigla + ";" + super.toString() + ";" + mechanicalPower;
	}
}
