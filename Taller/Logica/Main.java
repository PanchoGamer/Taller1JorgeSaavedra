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
		
		String[] Usua = new String[3];

		// Se crea un Scanner para la lectura del teclado
		Scanner s = new Scanner(System.in);
		// Scanner sArchR = new Scanner(fileR);

		int option = 0;
		do {

			// Menu :D
			System.out.println("\n1) Menu de Usuarios");
			System.out.println("2) Menu de analisis");
			System.out.println("3) Salir");

			System.out.print(">");
			String entrada = s.nextLine();

			// Transformacion de string a int
			option = Integer.parseInt(entrada);
			
			int c = 0;
			
			// Menu de Usuario
			if (option == 1) {

				// Pregunta usuario y contraseña para luego verificar con el TXT correspondiente
				System.out.print("\nUsuario: ");
				String Usuario = s.nextLine();
				System.out.print("Contraseña: ");
				String Contra = s.nextLine();
				
				boolean pass = false;
				
				int opcion = 0;
				
				
				try {
					// Scanner para el usuario
					Scanner sArchU = new Scanner(fileU);

					while (sArchU.hasNextLine()) {
						String line = sArchU.nextLine();
						String[] partes = line.split(";");
						
						Usua[c] = partes[0];

						if (Usuario.equals(partes[0])) {
							if (Contra.equals(partes[1])) {
								pass = true;
							}
							
						}
						c++;

					}
					if (pass) {
						System.out.println("\nAcceso Correcto!");
						System.out.printf("\nBienvenido %s\n", Usuario);
						do {
							
							try {
								System.out.println("\nQue deseas realizar? \n");
								System.out.println("1) Registrar actividad");
								System.out.println("2) Modificar actividad");
								System.out.println("3) Eliminar actividad");
								System.out.println("4) Cambiar contraseña");
								System.out.println("5) Salir");
								System.out.print(">");
								
								String entry = s.nextLine();
								
								opcion = Integer.parseInt(entry);
								
							} catch (Exception e) {
								System.out.println("Formato incorrecto, vuelva a ingresar");
							}
							
							if (opcion == 1) {
								try {
									Scanner sArchR = new Scanner(fileR);
									int totalRegistro = 0;
									while(sArchR.hasNextLine()) {
										sArchR.nextLine();
										totalRegistro++;
									}
									if (totalRegistro < 300) {
										FileWriter fw = new FileWriter(fileR, true);
										BufferedWriter bw = new BufferedWriter(fw);
										
										System.out.print("Ingrese fecha (DD/MM/AAAA): ");
										String fecha = s.nextLine();
										System.out.print("\nIngrese horas:");
										String horas = s.nextLine();
										System.out.print("\nIngrese actividad: ");
										String actividad = s.nextLine();
										
										String ladd = (Usuario + ";" + fecha + ";" + horas + ";" + actividad);
										
										bw.newLine();
										bw.write(ladd);
										
										bw.close();
										sArchR.close();
									} else {
										System.out.println("Error, no se puede tener mas de 300 actividades");
									}
								
									
								} catch (IOException e) {
									System.out.println("Error con Registros.txt");
								}
								
								
							}
							
							if (opcion == 2) {
								int contTexto = 0;
								int cont = 1;
								int nlinea = 0;
								String [] lineas = new String[300];
								int[] nlineas = new int[300];
								int[] nlineasR = new int[300];
								System.out.println("\nIndique linea a modificar\n");
								boolean est = true;
								while (est) {
									try {
										Scanner sArchR = new Scanner(fileR);
										while (sArchR.hasNextLine()){
											String line = sArchR.nextLine();
											String[] partes = line.split(";");
											
											lineas[contTexto] = line;
											
											nlineas[cont] = cont;
											
											if (Usuario.equals(partes[0])) {
												System.out.println(cont+")"+" "+line);
												nlineasR[cont] = contTexto; 
												cont++;
												
											}
											contTexto++;
										}
										
										sArchR.close();
									} catch (Exception e) {
										System.out.println("No existe el archivo Registros.txt");
									}
									
									System.out.print("\n> ");
									
									try {
										String ent = s.nextLine();
										nlinea = Integer.parseInt(ent);
										
										
										
										
									} catch (Exception e) {
										System.out.println("Ingrese un numero valido de linea");
									}
									if (nlinea > cont) {
										System.out.println("Error seleccione una linea valida");
									} else {
										System.out.print("Indique fecha (DD/MM/AAAA): ");
										String fecha = s.nextLine();
										
										System.out.print("Indique horas: ");
										String horas = s.nextLine();
										
										System.out.print("Indique actividad: ");
										String activ = s.nextLine();
										
										String lineam = (Usuario + ";" + fecha + ";" + horas + ";" + activ);
										
										int indiceR = nlineasR[nlinea];
										lineas[indiceR] = lineam;
										
										BufferedWriter bw = new BufferedWriter(new FileWriter(fileR));
										
										for (int i = 0; i < contTexto; i++) {
											bw.write(lineas[i]);
											if (i < contTexto -1) {
												bw.newLine();
											}
										}
										
										bw.close();
										
										est = false;
										
									}
									
								}
							}
						
							
						} while (opcion != 5);
						
					} else {
						System.out.println("Datos Incorrectos");
					}


					sArchU.close();
				} catch (Exception e) {
					System.out.println("no existe el archivo Usuarios.txt");
				}
			}
			else if (option == 2) {
				int opcion = 0;
				do {
					System.out.printf("\nBienvenido al menu de analisis!");
					System.out.println("\nQue deseas realizar? \n");
					System.out.println("1) Actividad más realizada");
					System.out.println("2) Actividad más realizada por cada usuario");
					System.out.println("3) Usuario con mayor procastinacion");
					System.out.println("4) Ver todas las actividades");
					System.out.println("5) Salir");
					System.out.print(">");
					
					String entry = s.nextLine();
					
					try {
						opcion = Integer.parseInt(entry);
						
					} catch(Exception e) {
						System.out.println("Formato Incorrecto, vuelva presionar para volver al menu");
						entry = s.nextLine();
					}
					
					if (opcion == 4) {
						try {
							Scanner sArchR = new Scanner(fileR);
							
							while (sArchR.hasNextLine()) {
								String linea = sArchR.nextLine();
								System.out.println(linea);
							}
							sArchR.close();
						} catch(Exception e) {
							System.out.println("No existe el archivo");
						}
					}
					
				} while (opcion != 5);
			}
		} while (option != 3);

		s.close();

	}

}
