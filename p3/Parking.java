package p3;

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
					carSpace = new CarSpace[maxZone - 64][sizeZone];
					for (int i = 0; i < maxZone - 64; i++) {
						char letra = (char) (i + 65);
						for (int j = 0; j < sizeZone; j++) {
							int numero = j + 1;
							carSpace[i][j] = new CarSpace(new Coordinate(letra, numero), null);
						}
					}
				} else {
					String place = partes[0];
					Coordinate cocheAparcado = new Coordinate(place.charAt(0), Integer.parseInt(place.substring(1)));
					for (int i = 0; i < carSpace.length; i++) {
						for (int j = 0; j < carSpace[i].length; j++) {
							boolean aparcado = carSpace[i][j].getCoordinate().isEqualTo(cocheAparcado);
							if (aparcado == true) {
								carSpace[i][j].setPlate(partes[1]);
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
		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		PrintWriter pw = new PrintWriter(archivo);
		pw.println(maxZone + ";" + sizeZone + ";" + lowerElectricZone);
		for (int i = 0; i < carSpace.length; i++) {
			for (int j = 0; j < carSpace[i].length; j++) {
				if (carSpace[i][j].getPlate() != null) {
					pw.println(carSpace[i][j].toText());
				}
			}
		}
		pw.close();
	}

	public void carEntry(String plate, char carType) {

		for (int i = 0; i < carSpace.length; i++) {
			for (int j = 0; j < carSpace[i].length; j++) {
				if (carType == 'C') {
					if (carSpace[i][j].getPlate() == null
							&& carSpace[i][j].getCoordinate().getZone() - 64 <= lowerElectricZone - 65) {
						carSpace[i][j].setPlate(plate);
						return;
					}
				}
				if (carType == 'E') {
					if (carSpace[i][j].getPlate() == null
							&& carSpace[i][j].getCoordinate().getZone() - 64 > lowerElectricZone - 65) {
						carSpace[i][j].setPlate(plate);
						return;
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
