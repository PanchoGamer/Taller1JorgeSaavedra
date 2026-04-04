/// Jorge Saavedra Sanchez 
/// 22.347.590-6 
/// Ingenieria en Tecnologias de Informacion

package Logica;

// Importamos librerias necesarias
import java.io.*;
import java.util.*;

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
									sArchR.close();
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
									sArchR.close();
									
								} catch (IOException e) {
									System.out.println("Error con Registros.txt");
								}
								
								
							}
							
							else if (opcion == 2) {
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
										
										if (nlinea > cont) {
											System.out.println("Error, seleccione solo los numeros presentados");
											break;
										} else if (nlinea < 0){
											System.out.println("Error, seleccione solo los numeros presentados");
											break;
										}
										
										
										
									} catch (Exception e) {
										System.out.println("Ingrese un numero valido de linea");
									}
									if (nlinea > cont && nlinea < 1) {
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
							else if (opcion == 3) {
								boolean est = true;
								System.out.println("Seleccione linea a eliminar ");
								int opc = 0;
								int cont = 0;
								int cMuestra = 1;
								String[] lineas = new String[300];
								int[] nlineas = new int[300];
								int[] nlineasR = new int[300];
								while (est) {
									Scanner sArchR = new Scanner(fileR);
									while (sArchR.hasNextLine()) {
										String linea = sArchR.nextLine();
										String[] partes = linea.split(";");
										lineas[cont] = linea;
										nlineas[cont] = cont;
										
										if (Usuario.equals(partes[0])) {
											System.out.println(cMuestra+")"+" "+linea);
											nlineasR[cMuestra] = cont;
											cMuestra++;
										}
										cont++;
										
									}
	
									System.out.print(">");
									String entr = s.nextLine();
									
									try {
										opc = Integer.parseInt(entr);
										
										int indiceR = nlineasR[opc];
										
										
										if (opc > 300 || opc < 1) {
											System.out.println("Error, elija entre las activides");
										} else {
											BufferedWriter bw = new BufferedWriter(new FileWriter(fileR));
											for (int i =0; i < cont; i++) {
												if (i != indiceR) {
													bw.write(lineas[i]);
													bw.newLine();
												}
											}
											bw.close();
										}
									} catch (Exception e) {
										System.out.println("Error ingrese un numero valido");
									}
									
									sArchR.close();
									est = false;
								}
							}
							else if (opcion == 4) {
								String[] Usuarios = new String[10];
								String[] Contrase = new String[10];
								System.out.print("Ingrese nueva contraseña: ");
								String ncontra = s.nextLine();
								int cont = 0;
								try {
									Scanner sArchU2 = new Scanner(fileU);
									
									while (sArchU2.hasNextLine()) {
										String linea = sArchU2.nextLine();
										String[] partes = linea.split(";");
										Usuarios[cont] = partes[0];
										if (partes[0].equals(Usuario)) {
											Contrase[cont] = ncontra;
										} else {
											Contrase[cont] = partes[1];
										}
										cont++;
									}
									sArchU2.close();
									BufferedWriter bw = new BufferedWriter(new FileWriter(fileU));
									for(int i = 0; i < cont; i++) {
										String linea = Usuarios[i] + ";" + Contrase[i];
										bw.write(linea);
										if (i < cont-1) {
											bw.newLine();	
										}
									}
									bw.close();
									
									
								} catch(IOException e) {
									System.out.println("No se encontro Usuarios.txt");
								}
								
							}
							
						} while (opcion != 5);
						
					} else {
						System.out.println("Datos Incorrectos");
					}


					sArchU.close();
				} catch (IOException e) {
					System.out.println("No existe el archivo Usuarios.txt");
				}
			}
			else if (option == 2) {
				int opcion = 0;
				System.out.printf("\nBienvenido al menu de analisis!");
				do {
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
					
					if (opcion == 1) {
						String[] activunica = new String[500];
						int[] conteoactividades = new int[500];
						try {
							Scanner sArchR = new Scanner(fileR);
							int cont = 0;
							while (sArchR.hasNextLine()) {
								String linea = sArchR.nextLine();
								String[] partes = linea.split(";");
								String actividad = partes[3];
								boolean encontrado = false;
								
								for (int i = 0; i < cont; i++) {
									if (actividad.equalsIgnoreCase(activunica[i])) {
										conteoactividades[i]++;
										encontrado = true;
										break;
									}
								}
								if (!encontrado && cont < 500) {
									activunica[cont] = actividad;
									conteoactividades[cont] = 1;
									cont++;
								}
							}
							sArchR.close();
							
							int max = -1;
							String maxn = "";
							
							for (int i = 0; i < cont; i++) {
								if (conteoactividades[i] > max) {
									max = conteoactividades[i];
									maxn = activunica[i];
								}
							}
							System.out.printf("\nLa actividad que mas se repite es %s, un total de %d veces\n", maxn, max);
							
							
						}catch (FileNotFoundException e) {
							System.out.println("No existe Registros.txt");
						}
					} else if (opcion == 4) {
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
