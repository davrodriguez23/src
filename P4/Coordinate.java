package P4;

import java.util.Objects;

public class Coordinate {
	private char zone;
	private int number;

	public Coordinate(char zone, int number) {
		super();
		this.zone = zone;
		this.number = number;
	}

	public char getZone() {
		return zone;
	}

	public void setZone(char zone) {
		this.zone = zone;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean equals(Object c) {
		if (this == c)
			return true;
		if (c == null)
			return false;
		if (getClass() != c.getClass())
			return false;
		Coordinate other = (Coordinate) c;
		return number == other.number && Objects.equals(zone, other.zone);
	}

	public String toText() {
		return zone + Integer.toString(number);
	}
}
