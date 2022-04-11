package P4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Parking {
	private char maxZone;
	private int sizeZone;
	private char lowerElectricZone;
	private CarSpace carSpace[][];

	public Parking(String nameFile) throws FileNotFoundException {
		File archivo = new File(nameFile);
		Scanner linea = new Scanner(archivo);
		while (linea.hasNextLine()) {
			String lineaLeida = linea.nextLine();
			if (lineaLeida.startsWith("#") == false) {
				String[] partes = lineaLeida.split(";");
				if (partes.length == 3) {
					maxZone = partes[0].charAt(0);
					sizeZone = Integer.parseInt(partes[1]);
					lowerElectricZone = partes[2].charAt(0);
					carSpace = new CarSpace[maxZone - 64][sizeZone + 1];
					for (int i = 0; i < maxZone - 64; i++) {
						char letra = (char) (i + 65);
						for (int j = 0; j < sizeZone; j++) {
							int numero = j + 1;
							if (i > lowerElectricZone - 66) {
								carSpace[i][j] = new ElectricCarSpace(new Coordinate(letra, numero), null,
										new ElectricCharger(false));
							} else {
								carSpace[i][j] = new CarSpace(new Coordinate(letra, numero), null);
							}
						}
					}
				}
			}
		}
		linea.close();
	}

	public void saveParking(String fileName) throws IOException {
		File archivo = new File(fileName);
		PrintWriter pw = new PrintWriter(archivo);
		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		pw.println(maxZone + ";" + sizeZone + ";" + lowerElectricZone);
		for (int i = 0; i < carSpace.length; i++) {
			for (int j = 0; j < carSpace[i].length - 1; j++) {
				if (carSpace[i][j].getPlate() != null) {
					pw.println(carSpace[i][j].toString());
				}

			}
		}
		pw.close();
	}

	public void carEntry(Car car) {
		for (int i = 0; i < carSpace.length; i++) {
			for (int j = 0; j < carSpace[i].length; j++) {
				if (car instanceof CombustionCar) {
					if (carSpace[i][j].getPlate() == null) {
						carSpace[i][j].setPlate(car.getPlate());
						return;
					}
				}

				if (car instanceof ElectricCar || car instanceof HybridCar) {
					CarSpace s = carSpace[i][j];
					if (s instanceof ElectricCarSpace) {
						if (s.getPlate() == null) {
							ElectricCarSpace c = (ElectricCarSpace) s;
							c.setPlate(car.getPlate());
							c.getCharger().connect();
							carSpace[i][j] = c;
							return;
						}
					}
				}
			}
		}
	}

	public void carDeparture(String matricula) {
		for (int i = 0; i < carSpace.length; i++) {
			for (int j = 0; j < carSpace[i].length; j++) {
				if (carSpace[i][j].getPlate() != null && carSpace[i][j].getPlate().equalsIgnoreCase(matricula)) {
					carSpace[i][j].setPlate(null);
					CarSpace s = carSpace[i][j];
					if (s instanceof ElectricCarSpace) {
						ElectricCarSpace c = (ElectricCarSpace) s;
						c.getCharger().disconnect();
					}
					return;
				}
			}
		}
	}

	public String toMap() {
		String mapa = "";
		for (int i = 0; i < carSpace.length; i++) {
			for (int j = 0; j < carSpace[i].length; j++) {
				if (carSpace[i][j].getCoordinate().getZone() - 64 <= lowerElectricZone - 65) {
					if (carSpace[i][j].getPlate() == null) {

						mapa = mapa + carSpace[i][j].getCoordinate().getZone()
								+ carSpace[i][j].getCoordinate().getNumber() + "          |";
					} else {
						mapa = mapa + carSpace[i][j].getCoordinate().getZone()
								+ carSpace[i][j].getCoordinate().getNumber() + "   " + carSpace[i][j].getPlate() + "|";
					}
				}
				if (carSpace[i][j].getCoordinate().getZone() - 64 > lowerElectricZone - 65) {
					if (carSpace[i][j].getPlate() == null) {
						mapa = mapa + carSpace[i][j].getCoordinate().getZone()
								+ carSpace[i][j].getCoordinate().getNumber() + " E        |";
					} else {
						mapa = mapa + carSpace[i][j].getCoordinate().getZone()
								+ carSpace[i][j].getCoordinate().getNumber() + " E " + carSpace[i][j].getPlate() + "|";
					}
				}
			}
			mapa = mapa + "\n";
		}
		return mapa;
	}

	public char getMaxZone() {
		return maxZone;
	}

	public void setMaxZone(char maxZone) {
		this.maxZone = maxZone;
	}

	public int getSizeZone() {
		return sizeZone;
	}

	public void setSizeZone(int sizeZone) {
		this.sizeZone = sizeZone;
	}

	public char getLowerElectricZone() {
		return lowerElectricZone;
	}

	public void setLowerElectricZone(char lowerElectricZone) {
		this.lowerElectricZone = lowerElectricZone;
	}

	public CarSpace[][] getCarSpace() {
		return carSpace;
	}

	public void setCarSpace(CarSpace[][] carSpace) {
		this.carSpace = carSpace;
	}

}
