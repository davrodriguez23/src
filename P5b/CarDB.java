package P5b;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CarDB {
	ArrayList<Car> cityCars = new ArrayList<Car>();

	public void increaseCarBatteryChargeLevel(String plate, String entryTime, String departureTime) {
		for (int i = 0; i < cityCars.size(); i++) {
			if (cityCars.get(i).getPlate().equals(plate)) {
				if (cityCars.get(i) instanceof HybridCar) {
					float intervalo = intervalInHours(entryTime, departureTime);
					HybridCar car = (HybridCar) cityCars.get(i);
					car.increaseBatteryChargeLevel(intervalo);
				}
				if (cityCars.get(i) instanceof ElectricCar) {
					float intervalo = intervalInHours(entryTime, departureTime);
					ElectricCar car = (ElectricCar) cityCars.get(i);
					car.increaseBatteryChargeLevel(intervalo);
				}
			}
		}
	}

	private float intervalInHours(String inTime, String outTime) {
		int hi = Integer.parseInt(inTime.split(":")[0].trim());
		int mi = Integer.parseInt(inTime.split(":")[1].trim());
		int ho = Integer.parseInt(outTime.split(":")[0].trim());
		int mo = Integer.parseInt(outTime.split(":")[1].trim());
		int dif = (ho * 60 + mo) - (hi * 60 + mi);
		return ((float) dif / 60);
	}

	public float computeAverageBatteryLevel() {
		float cargaBateriaMedia = 0f;
		int contador = 0;
		for (int i = 0; i < cityCars.size(); i++) {
			if (cityCars.get(i) instanceof HybridCar) {
				cargaBateriaMedia += ((HybridCar) cityCars.get(i)).getBatteryCharge();
				contador++;
			}
			if (cityCars.get(i) instanceof ElectricCar) {
				cargaBateriaMedia += ((ElectricCar) cityCars.get(i)).getBatteryCharge();
				contador++;
			}
		}
		cargaBateriaMedia = cargaBateriaMedia / contador;
		return cargaBateriaMedia;
	}

	public int computeTotalPower() {
		int power = 0;
		for (int i = 0; i < cityCars.size(); i++) {

			if (cityCars.get(i) instanceof CombustionCar || cityCars.get(i) instanceof ElectricCar
					|| cityCars.get(i) instanceof HybridCar) {
				power += cityCars.get(i).getTotalPower();
				System.out.println(cityCars.get(i).getTotalPower());
			}
		}
		return power;
	}

	public Car getCarFromPlate(String plate) {
		Car coche = null;
		for (int i = 0; i < cityCars.size(); i++) {
			if (cityCars.get(i).getPlate().equalsIgnoreCase(plate)) {
				coche = cityCars.get(i);
				return coche;
			}
		}
		return coche;
	}

	public void readCityCarsFile() throws FileNotFoundException {
		File archivo = new File("cityCars.txt");
		Scanner entradaConsola = new Scanner(archivo);
		while (entradaConsola.hasNextLine()) {
			String linea = entradaConsola.nextLine();
			System.out.println(linea);
			if (linea.startsWith("#") == false) {
				String[] partes = linea.split(";");
				if (partes[0].equalsIgnoreCase("C")) {
					boolean valido = CombustionCar.isValidPlate(partes[1]);
					boolean valido1 = CombustionCar.isValidPower(Integer.parseInt(partes[3]));
					if (valido == true && valido1 == true) {
						Car c = new CombustionCar(partes[1], partes[2], Integer.parseInt(partes[3]));
						cityCars.add(c);
					}
				}
				if (partes[0].equalsIgnoreCase("H")) {
					boolean valido = HybridCar.isValidPlate(partes[1]);
					boolean valido1 = HybridCar.isValidMechanicalPower(Integer.parseInt(partes[3]));
					boolean valido2 = HybridCar.isValidPower(Integer.parseInt(partes[4]));
					boolean valido3 = HybridCar.isValidBattery(Float.parseFloat(partes[5]));
					if (valido == true && valido1 == true & valido2 == true && valido3 == true) {
						Car c = new HybridCar(partes[1], partes[2], Integer.parseInt(partes[3]),
								Integer.parseInt(partes[4]), Float.parseFloat(partes[5]));
						cityCars.add(c);
					}
				}
				if (partes[0].equalsIgnoreCase("E")) {
					boolean valido = ElectricCar.isValidPlate(partes[1]);
					boolean valido1 = ElectricCar.isValidPower(Integer.parseInt(partes[3]));
					boolean valido2 = ElectricCar.isValidBattery(Float.parseFloat(partes[4]));
					if (valido == true && valido1 == true && valido2 == true) {
						Car c = new ElectricCar(partes[1], partes[2], Integer.parseInt(partes[3]),
								Float.parseFloat(partes[4]));
						cityCars.add(c);
					}
				}
			}
		}
		entradaConsola.close();
		return;
	}

	public void saveCarsToFile(String filename) throws IOException {
		File archivo = new File(filename);
		PrintWriter pw = new PrintWriter(archivo);
		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		for (int i = 0; i < cityCars.size(); i++) {
			if (cityCars.get(i).getPlate() != null) {
				pw.println(cityCars.get(i).toString());
			}
		}
		pw.close();
	}
}
