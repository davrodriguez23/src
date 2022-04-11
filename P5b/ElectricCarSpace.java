package P5b;

public class ElectricCarSpace extends CarSpace {
	
	private ElectricCharger charger;
	public ElectricCarSpace(Coordinate coordinate, String plate, String entryTime, ElectricCharger charger) {
		super(coordinate, plate, entryTime);
		this.charger = charger;
	}

	public ElectricCharger getCharger() {
		return charger;
	}

	public void setCharger(ElectricCharger charger) {
		this.charger = charger;
	}

	public String toString() {
	return super.getCoordinate().getZone() + "" + super.getCoordinate().getNumber() + ";" + getPlate()+";"+super.getEntryTime()+";"+charger.isConnected();
	}
	
	
}
