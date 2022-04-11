package P5b;

public class CarSpace implements Comparable<CarSpace> {
	private Coordinate coordinate;
	private String plate;
	private String entryTime;

	public CarSpace(Coordinate coordinate, String plate, String entryTime) {
		this.coordinate = coordinate;
		this.plate = plate;
		this.entryTime = entryTime;
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

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String toString() {
		return coordinate.getZone() + "" + coordinate.getNumber() + ";" + plate + ";" + entryTime;
	}
	@Override
	public int compareTo(CarSpace c) {
		return c.getCoordinate().compareTo(coordinate);
	}
}
