package P5b;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class P5b {
	static Parking parking;
	static CarDB cdb = new CarDB();

	public static void main(String[] args) throws IOException {
		cdb.readCityCarsFile();
		cdb.saveCarsToFile(args[3]);
		int potTotal = cdb.computeTotalPower();
		System.out.println("Total power = " + potTotal);
		float bateriaMedia = cdb.computeAverageBatteryLevel();
		System.out.println("Median Battery Charge Level = " + bateriaMedia);
		parking = new Parking(args[0]);
		processIO(args[1]);
		parking.saveParking(args[2]);
		String mapa = parking.toMap();
		System.out.println(mapa);
	}

	public static void processIO(String fileName) throws FileNotFoundException {
		File archivo = new File(fileName);
		Scanner entrada = new Scanner(archivo);
		while (entrada.hasNextLine()) {
			String linea = entrada.nextLine();
			String[] partes = linea.split(";");
			if (partes[0].equalsIgnoreCase("I")) {
				String plate = partes[1];
				String time = partes[2];
				Car car = cdb.getCarFromPlate(plate);
				parking.carEntry(car,time);
			}
			if(partes[0].equalsIgnoreCase("O")) {
				parking.carDeparture(partes[1]);
			}
		}
		entrada.close();
	}
}
