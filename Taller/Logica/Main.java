/// Jorge Saavedra Sanchez 
/// 22.347.590-6 
/// Ingenieria en Tecnologias de Informacion

package Logica;

// Importamos librerias necesarias
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {

		// Se seleccionas los archivos necesarios
		File fileU = new File("txts/Usuarios.txt");
		File fileR = new File("txts/Registros.txt");

		// Se crea un Scanner para la lectura del teclado
		Scanner s = new Scanner(System.in);
		// Scanner sArchR = new Scanner(fileR);

		int option = 0;
		do {

			// Menu :D
			System.out.println("1) Menu de Usuarios");
			System.out.println("2) Menu de analisis");
			System.out.println("3) Salir");

			System.out.print(">");
			String entrada = s.nextLine();

			// Transformacion de string a int
			option = Integer.parseInt(entrada);

			// Menu de Usuario
			if (option == 1) {

				// Pregunta usuario y contraseña para luego verificar con el TXT correspondiente
				System.out.print("Usuario: ");
				String Usuario = s.nextLine();
				System.out.print("Contraseña: ");
				String Contra = s.nextLine();

				try {
					// Scanner para el usuario
					Scanner sArchU = new Scanner(fileU);

					while (sArchU.hasNextLine()) {
						String line = sArchU.nextLine();
						String[] partes = line.split(";");

						int opcion = 0;
						System.out.println(partes[0]);
						System.out.println(partes[1]);

						if (Usuario == partes[0] && Contra == partes[1]) {
							do {

								try {
									System.out.printf("Bienvenido %s \n", Usuario);
									System.out.println("Que deseas realizar? \n");
									System.out.println("1) Registrar actividad");
									System.out.println("2) Modificar actividad");
									System.out.println("3) Eliminar actividad");
									System.out.println("4) Cambiar contraseña");
									System.out.println("5) Salir");

									String entry = s.nextLine();

									opcion = Integer.parseInt(entry);

								} catch (InputMismatchException e) {
									System.out.println("Formato incorrecto, vuelva a ingresar");
								}

							} while (opcion != 5);

						} else {
							System.out.println("Datos Incorrectos");
						}

					}

				} catch (Exception e) {
					System.out.println("no existe el archivo");
				}
			}

		} while (option != 3);

		s.close();

	}

}
