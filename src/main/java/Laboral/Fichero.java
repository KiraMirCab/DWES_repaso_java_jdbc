package Laboral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Fichero {
	private String nombreFichero = "";
	private File fichero;
	private FileReader fr;
	private BufferedReader br;
	private FileWriter fw;
	private PrintWriter pw;
	private boolean abierto = false;

	public Fichero(String nombre) {
		if (nombre != null) {
			nombreFichero = nombre;
		}
	}

	public void abrir() {
		try {
			fichero = new File(nombreFichero);
			fr = new FileReader(fichero);
			br = new BufferedReader(fr);
			fw = new FileWriter(fichero);
			pw = new PrintWriter(fw);
			abierto = true;
		} catch (FileNotFoundException e) {
			System.out.println("El " + nombreFichero + " no se pudo abrir porque no se encuentra");
		} catch (IOException ex) {
			System.out.println("Error al abrir el fichero");
		}
	}

	public void cerrar() {
		if (abierto) {
			try {
				br.close();
				fr.close();
				pw.close();
				fw.close();
				abierto = false;
			} catch (IOException e) {
				System.out.println("El fichero no se pudo cerrar");
			}
		} else {
			throw new IllegalStateException("El fichero no está abierto");
		}
	}

	/**
	 * Este metodo imprime por consola todas las lineas del fichero
	 */
	public void imprimeContenido() {

		if (abierto) {
			try {
				String linea = br.readLine();
				while (linea != null) {
					System.out.println(linea);
					linea = br.readLine();
				}
			} catch (IOException e) {
				System.out.println("No se puede mostrar el contenido. Se ha producido un error al leer del archivo");
			}
		} else {
			throw new IllegalStateException("El fichero no está abierto");
		}
	}

	/**
	 * Este método escribe una linea en el fichero
	 * 
	 * @param linea
	 */
	public void escribeTexto(String linea) {
		if (abierto) {
			if (linea != null) {
				pw.println(linea);
				pw.flush();
			}
		} else {
			throw new IllegalStateException("El fichero no está abierto");
		}
	}

	/**
	 * Este método interpreta las líneas del fichero y las convierte en Empleados
	 */
	public ArrayList<Empleado> leerFicheroEmpleados() {
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		if (abierto) {

			try {
				fr = new FileReader(fichero);
				br = new BufferedReader(fr);

				String linea = br.readLine();
				
				while (linea != null) {
					String[] palabras = linea.split(", ");
					Empleado emp = null;
					
					for (String string : palabras) {
						String dni = palabras[0];
						String nombre = palabras[1];
						char sexo = palabras[2].charAt(0);
						int categoria = Integer.parseInt(palabras[3]);
						int anyos = Integer.parseInt(palabras[4]);
					try {
						emp = new Empleado(dni, nombre, sexo, categoria, anyos);
					} catch (DatosNoCorrectosException e) {
						System.out.println("Datos incorrectos, no se puede crear un empleado");
						e.printStackTrace();
						}
					}
					empleados.add(emp);
					linea = br.readLine();
				}
			} catch (IOException e) {
				System.out.println("No se puede mostrar el contenido. Se ha producido un error al leer del archivo");
			}
		} else {
			throw new IllegalStateException("El fichero no está abierto");
		}
		return empleados;		
	}

	/**
	 * Método en desarrollo
	 * @param lineaAntigua
	 * @param lineaNueva
	 * @return
	 */
	public void actualizarFichero(String lineaAntigua, String lineaNueva) { 
		File fich = new File(nombreFichero);
//		Fichero fich1 = new Fichero(nombreFichero);
		
		if (abierto) { 
			try	{ 
				fr = new FileReader(fichero);
				br = new BufferedReader(fr);
				
				fw = new FileWriter(fich);
				pw = new PrintWriter(fw);
				String linea = br.readLine();
				
				while (linea != null) {
					if (linea.equalsIgnoreCase(lineaAntigua)) {
						pw.write(lineaNueva);
					} else {
						pw.write(linea);
					}
				System.out.println(linea);
				linea = br.readLine();
				}
			} catch (IOException e) { 
				System.out.println("No se puede mostrar el contenido. Se ha producido un error al leer del archivo"); 
				} 
			} else { 
				throw new IllegalStateException("El fichero no está abierto");
	  } 
 }
}
