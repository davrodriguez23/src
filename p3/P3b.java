package p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P3b {

	public static void main(String[] args) throws IOException {
		Parking p = new Parking(args[0]);
		File archivo = new File(args[1]);
		Scanner entrada = new Scanner(archivo);
		while (entrada.hasNextLine()) {
			String linea = entrada.nextLine();
			String[] partes = linea.split(";");
			if (partes[0].equalsIgnoreCase("I")) {
				p.carEntry(partes[1], partes[2].charAt(0));
			}
			if (partes[0].equalsIgnoreCase("O")) {
				p.carDeparture(partes[1]);
			}
		}

		p.saveParking(args[2]);
		entrada.close();
		String mapa = p.toMap();
		File archivo1 = new File("ParkingMap.txt");
		PrintWriter pw = new PrintWriter(archivo1);
		pw.print(mapa);
		pw.close();
	}

}
