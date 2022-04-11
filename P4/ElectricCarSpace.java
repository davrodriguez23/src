package P4;

public class ElectricCarSpace extends CarSpace {
	
	private ElectricCharger charger;
	public ElectricCarSpace(Coordinate coordinate, String plate, ElectricCharger charger) {
		super(coordinate, plate);
		this.charger = charger;
	}

	public ElectricCharger getCharger() {
		return charger;
	}

	public void setCharger(ElectricCharger charger) {
		this.charger = charger;
	}

	public String toString() {
	return super.getCoordinate().getZone() + "" + super.getCoordinate().getNumber() + ";" + getPlate()+" "+charger.isConnected();
	}
	
	
}
