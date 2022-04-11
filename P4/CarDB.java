package P4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CarDB {
	Car[] cityCars = new Car[100];

	public float computeAverageBatteryLevel() {
		float cargaBateriaMedia = 0f;
		int contador = 0;
		for (int i = 0; i < cityCars.length; i++) {
			if (cityCars[i] instanceof HybridCar) {
				cargaBateriaMedia += ((HybridCar) cityCars[i]).getBatteryCharge();
				contador++;
			}
			if (cityCars[i] instanceof ElectricCar) {
				cargaBateriaMedia += ((ElectricCar) cityCars[i]).getBatteryCharge();
				contador++;
			}
		}
		cargaBateriaMedia = cargaBateriaMedia / contador;
		return cargaBateriaMedia;
	}

	public int computeTotalPower() {
		int power = 0;
		for (int i = 0; i < cityCars.length; i++) {
			
			if (cityCars[i] instanceof CombustionCar || cityCars[i] instanceof ElectricCar
					|| cityCars[i] instanceof HybridCar) {
				power += cityCars[i].getTotalPower();
				System.out.println(cityCars[i].getTotalPower());
			}
		}
		return power;
	}

	public Car getCarFromPlate(String plate) {
		Car coche = null;
		for (int i = 0; i < cityCars.length; i++) {
			if (cityCars[i].getPlate().equalsIgnoreCase(plate)) {
				coche = cityCars[i];
				return coche;
			}
		}
		return coche;
	}

	public void readCityCarsFile() throws FileNotFoundException {
		File archivo = new File("cityCars.txt");
		Scanner entradaConsola = new Scanner(archivo);
		int i0 = 0;
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
						cityCars[i0] = c;
						i0++;
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
						cityCars[i0] = c;
						i0++;
					}
				}
				if (partes[0].equalsIgnoreCase("E")) {
					boolean valido = ElectricCar.isValidPlate(partes[1]);
					boolean valido1 = ElectricCar.isValidPower(Integer.parseInt(partes[3]));
					boolean valido2 = ElectricCar.isValidBattery(Float.parseFloat(partes[4]));
					if (valido == true && valido1 == true && valido2 == true) {
						Car c = new ElectricCar(partes[1], partes[2], Integer.parseInt(partes[3]),
								Float.parseFloat(partes[4]));
						cityCars[i0] = c;
						i0++;
					}
				}
			}
		}
		entradaConsola.close();
		return;
	}
}
