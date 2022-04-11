package P5b;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Parking {
	private char maxZone;
	private int sizeZone;
	private char lowerElectricZone;
	private HashSet<CarSpace> busyCarSpace = new HashSet<CarSpace>();
	private TreeSet<CarSpace> freeCarSpace = new TreeSet<CarSpace>();
	CarSpace inicioPlaza;

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
					for (int i = 0; i < maxZone - 64; i++) {
						char letra = (char) (i + 65);
						for (int j = 0; j < sizeZone; j++) {
							int numero = j + 1;
							if (i > lowerElectricZone - 66) {
								CarSpace nuevaPlaza = new ElectricCarSpace(new Coordinate(letra, numero), null, null,
										new ElectricCharger(false));

								freeCarSpace.add(nuevaPlaza);
								// System.out.println(freeCarSpace);
							} else {
								freeCarSpace.add(new CarSpace(new Coordinate(letra, numero), null, null));
								// System.out.println(freeCarSpace);
							}
						}
					}
				}
			}
		}
		System.out.println("PARKING INICIAL" + freeCarSpace);
		linea.close();
	}

	public void saveParking(String fileName) throws IOException {
		File archivo = new File(fileName);
		PrintWriter pw = new PrintWriter(archivo);
		TreeSet<CarSpace> busyCarSpaceTree = new TreeSet<CarSpace>();
		busyCarSpaceTree.addAll(busyCarSpace);
		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		pw.println(maxZone + ";" + sizeZone + ";" + lowerElectricZone);
		pw.println(busyCarSpaceTree);
		pw.close();
	}

	public void carEntry(Car car, String time) {
		TreeSet<CarSpace> freeCarSpaceCombustion = new TreeSet<CarSpace>();
		TreeSet<CarSpace> freeCarSpaceElectric = new TreeSet<CarSpace>();
		inicioPlaza = new ElectricCarSpace(new Coordinate(lowerElectricZone, 0), null, null, null);
		freeCarSpaceCombustion = (TreeSet<CarSpace>) freeCarSpace.headSet(inicioPlaza);
		freeCarSpaceElectric = (TreeSet<CarSpace>) freeCarSpace.tailSet(inicioPlaza);
		if (car instanceof CombustionCar) {
			freeCarSpaceCombustion.first().setPlate(car.getPlate());
			freeCarSpaceCombustion.first().setEntryTime(time);
			busyCarSpace.add(freeCarSpaceCombustion.first());
			freeCarSpaceCombustion.pollFirst();

		}

		if (car instanceof ElectricCar || car instanceof HybridCar) {
			CarSpace plazaElectrica = freeCarSpaceElectric.first();
			ElectricCarSpace c = (ElectricCarSpace) plazaElectrica;
			c.setPlate(car.getPlate());
			c.setEntryTime(time);
			c.getCharger().connect();
			busyCarSpace.add(c);
			freeCarSpaceElectric.pollFirst();
		}

		System.out.println("PARKING ACTUALIZADO libre" + freeCarSpace);
		System.out.println("PARKING ACTUALIZADO ocupado" + busyCarSpace);

	}

	public void carDeparture(String matricula) {
		for (Iterator<CarSpace> recorrer = busyCarSpace.iterator(); recorrer.hasNext();) {
			CarSpace c = recorrer.next();
			System.out.println(c.toString());

			// System.out.println(c);
			String mat = c.getPlate();

			if (mat.equalsIgnoreCase(matricula)) {
				if (c.getClass().getSimpleName().equals("CarSpace")) {
					c.setEntryTime(null);
					freeCarSpace.add(c);
					recorrer.remove();
				}
				if (c.getClass().getSimpleName().equals("ElectricCarSpace")) {
					ElectricCarSpace cElectric = (ElectricCarSpace) c;
					cElectric.setEntryTime(null);
					cElectric.getCharger().disconnect();
					freeCarSpace.add(cElectric);
					recorrer.remove();
				}

			}
		}
		System.out.println("PARKING ACTUALIZADO libre despues de salida" + freeCarSpace);
		System.out.println("PARKING ACTUALIZADO ocupado despues de salida" + busyCarSpace);
	}

	public String toMap() {
		String mapa = "";
		TreeSet<CarSpace> busyCarSpaceTree = new TreeSet<CarSpace>();
		busyCarSpaceTree.addAll(busyCarSpace);
		busyCarSpaceTree.addAll(freeCarSpace);
		for (Iterator<CarSpace> recorrer = busyCarSpaceTree.iterator(); recorrer.hasNext();) {
			CarSpace c = recorrer.next();
			if (c.getClass().getSimpleName().equals("CarSpace")) {
				if (c.getPlate() == null) {

					mapa = mapa + c.getCoordinate().getZone() + c.getCoordinate().getNumber() + "          |";
				} else {
					mapa = mapa + c.getCoordinate().getZone() + c.getCoordinate().getNumber() + "   " + c.getPlate()
							+ "|";
				}
			}
			if (c.getClass().getSimpleName().equals("ElectricCarSpace")) {
				if (c.getPlate() == null) {
					mapa = mapa + c.getCoordinate().getZone() + c.getCoordinate().getNumber() + " E        |";
				} else {
					mapa = mapa + c.getCoordinate().getZone() + c.getCoordinate().getNumber() + " E " + c.getPlate()
							+ "|";
				}
			}
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

}
