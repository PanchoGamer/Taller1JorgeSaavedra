package Logica;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int option = 0;

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
			
		} while (option != 3);
		
		s.close();
	}

}
