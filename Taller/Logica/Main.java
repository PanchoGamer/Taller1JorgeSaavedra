package Logica;

import java.util.Scanner;

import java.io.*;

public class Main {

	static String[] usuarios = new String[20];
	static String[] contras = new String[20];

	public static void leerArchivo() {
		File file = new File("txts/Usuarios.txt");

		Scanner su = new Scanner(file);

		int x = 0;
		int y = 0;

		while (su.hasNextLine()) {

			String line = su.nextLine();
			String[] partes = line.split(";");

			String usua = partes[0];
			String contrase = partes[1];

			usuarios[x] = usua;
			contras[y] = contrase;

			x++;
			y++;

		}
		su.close();

	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		leerArchivo();

		for (int i = 0; i < 2; i++) {
			System.out.println(usuarios[i]);
			System.out.println(contras[i]);

		}

		int option = 0;
		String usuario = ("");
		String contra = ("");

		do {
			System.out.println("1) Menu de usuarios");
			System.out.println("2) Menu de analisis");
			System.out.println("3) Salir");
			System.out.print("> ");
			String entrada = s.nextLine();

			try {
				option = Integer.parseInt(entrada);

			} catch (Exception e) {
				System.out.println("\nPor favor ingrese solo numeros. \n");
			}

			if (option > 3 || option < 1) {
				System.out.println("\nError, elija entre los numeros mostrados \n");
			}

			if (option == 1) {
				do {
					System.out.print("Usuario: \n");
					usuario = s.nextLine();
					System.out.print("\nContraseña: ");
					contra = s.nextLine();

				} while (usuario == "Hola" && contra == "Papus");

			}

		} while (option != 3);

		s.close();
	}

}
