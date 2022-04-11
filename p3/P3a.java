package p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P3a {

	static CombustionCar[] arrayCombustion = new CombustionCar[25];
	static HybridCar[] arrayHybrid = new HybridCar[25];
	static ElectricCar[] arrayElectric = new ElectricCar[25];

	public static void main(String[] args) throws FileNotFoundException {
		P3a p3a = new P3a();
		ElectricCar c1 = new ElectricCar("1111KLS", "SEAT", 220, 30.5f);
		System.out.println(c1.toText());
		ElectricCar c2 = new ElectricCar("2222LSX", "FORD", 220, 30.5f);
		System.out.println(c2.toText());
		ElectricCar c3 = new ElectricCar("6288LYM", "Ford", 110, 120.5f);
		ElectricCar c4 = new ElectricCar("07451JMR", "Seat", 60, 10.1f);
		ElectricCar c5 = new ElectricCar("3400JXK", "Peugeot", 70, 50f);
		ElectricCar c6 = new ElectricCar("6288LYM", "Ford", 20, 10.1f);
		boolean plate3 = ElectricCar.isValidPlate(c3.getPlate());
		boolean power3 = ElectricCar.isValidPower(c3.getPower());
		boolean battery3 = ElectricCar.isValidBattery(c3.getBatteryCharge());
		boolean plate4 = ElectricCar.isValidPlate(c4.getPlate());
		boolean power4 = ElectricCar.isValidPower(c4.getPower());
		boolean battery4 = ElectricCar.isValidBattery(c4.getBatteryCharge());
		boolean plate5 = ElectricCar.isValidPlate(c5.getPlate());
		boolean power5 = ElectricCar.isValidPower(c5.getPower());
		boolean battery5 = ElectricCar.isValidBattery(c5.getBatteryCharge());
		boolean plate6 = ElectricCar.isValidPlate(c6.getPlate());
		boolean power6 = ElectricCar.isValidPower(c6.getPower());
		boolean battery6 = ElectricCar.isValidBattery(c6.getBatteryCharge());
		if (plate3 == true && power3 == true && battery3 == true) {
			System.out.println(c3.toText());
		}
		if (plate4 == true && power4 == true && battery4 == true) {
			System.out.println(c4.toText());
		}
		if (plate5 == true && power5 == true && battery5 == true) {
			System.out.println(c5.toText());
		}
		if (plate6 == true && power6 == true && battery6 == true) {
			System.out.println(c6.toText());
		}

		p3a.readCityCarsFile(args[0]);
		int totalPower = p3a.computeTotalPower();
		System.out.println("Suma de potencia = " + totalPower);
		writeElectricCityCarsFile(args[1]);
	}

	public void readCityCarsFile(String filename) throws FileNotFoundException {
		File archivo = new File(filename);
		Scanner entradaConsola = new Scanner(archivo);
		int i0 = 0, i1 = 0, i2 = 0;
		while (entradaConsola.hasNextLine()) {
			String linea = entradaConsola.nextLine();
			if (linea.startsWith("#") == false) {
				String[] partes = linea.split(";");
				if (partes[0].equalsIgnoreCase("C")) {
					boolean valido = CombustionCar.isValidPlate(partes[1]);
					boolean valido1 = CombustionCar.isValidPower(Integer.parseInt(partes[3]));
					if (valido == true && valido1 == true) {
						CombustionCar c = new CombustionCar(partes[1], partes[2], Integer.parseInt(partes[3]));
						arrayCombustion[i0] = c;
						i0++;
					}
				}
				if (partes[0].equalsIgnoreCase("H")) {
					boolean valido = HybridCar.isValidPlate(partes[1]);
					boolean valido1 = HybridCar.isValidMechanicalPower(Integer.parseInt(partes[3]));
					boolean valido2 = HybridCar.isValidPower(Integer.parseInt(partes[4]));
					boolean valido3 = HybridCar.isValidBattery(Float.parseFloat(partes[5]));
					if (valido == true && valido1 == true & valido2 == true && valido3 == true) {
						HybridCar h = new HybridCar(partes[1], partes[2], Integer.parseInt(partes[3]),
								Integer.parseInt(partes[4]), Float.parseFloat(partes[5]));
						arrayHybrid[i1] = h;
						i1++;
					}
				}
				if (partes[0].equalsIgnoreCase("E")) {
					boolean valido = ElectricCar.isValidPlate(partes[1]);
					boolean valido1 = ElectricCar.isValidPower(Integer.parseInt(partes[3]));
					boolean valido2 = ElectricCar.isValidBattery(Float.parseFloat(partes[4]));
					if (valido == true && valido1 == true && valido2 == true) {
						ElectricCar e = new ElectricCar(partes[1], partes[2], Integer.parseInt(partes[3]),
								Float.parseFloat(partes[4]));
						arrayElectric[i2] = e;
						i2++;
					}
				}
			}
		}
		entradaConsola.close();
		return;
	}

	public int computeTotalPower() {
		int totalPower = 0;
		for (int i = 0; i < arrayCombustion.length; i++) {
			if (arrayCombustion[i] != null) {
				totalPower = totalPower + arrayCombustion[i].getMechanicalPower();
			} else {
				i = arrayCombustion.length;
			}
			System.out.println("");
		}
		for (int i = 0; i < arrayHybrid.length; i++) {
			if (arrayHybrid[i] != null) {
				totalPower = totalPower + arrayHybrid[i].getMechanicalPower();
				totalPower = totalPower + arrayHybrid[i].getElectricPower();
			} else {
				i = arrayHybrid.length;
			}
		}
		for (int i = 0; i < arrayElectric.length; i++) {
			if (arrayElectric[i] != null) {
				totalPower = totalPower + arrayElectric[i].getPower();
			} else {
				i = arrayElectric.length;
			}
		}
		return totalPower;
	}

	public static void writeElectricCityCarsFile(String nombreArchivo) throws FileNotFoundException {
		File archivo1 = new File(nombreArchivo);
		PrintWriter pw = new PrintWriter(archivo1);
		for (int i = 0; i < arrayElectric.length; i++) {
			if (arrayElectric[i] != null) {
				arrayElectric[i].increaseBatteryChargeLevel(10f);
				pw.println(arrayElectric[i].toText());
			} else {
				i = arrayElectric.length;
				pw.close();
			}
		}
	}

}
