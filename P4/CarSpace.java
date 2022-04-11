package P4;

public class CarSpace {
	private Coordinate coordinate;
	private String plate;
	
	public CarSpace(Coordinate coordinate, String plate) {
		super();
		this.coordinate = coordinate;
		this.plate = plate;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	public String toString() {
		return coordinate.getZone() + "" + coordinate.getNumber() + ";" + plate;
	}
}
