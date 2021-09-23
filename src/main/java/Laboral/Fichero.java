package Laboral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

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

	public void imprimeContenido() {

		if (abierto) {
			try {
				String linea = br.readLine();
				while (linea != null) {
					System.out.println(linea);
					linea = br.readLine();
				};

			} catch (IOException e) {
				System.out.println("No se puede mostrar el contenido. Se ha producido un error al leer del archivo");
			}
		} else {
			throw new IllegalStateException("El fichero no está abierto");
		}
	}

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

	public void actualizarFichero(String dni, String categoriaParaCambiar, String valorNuevo) {
		if (abierto) {
			try {
				fr = new FileReader(fichero);
				br = new BufferedReader(fr);
				String linea = br.readLine();
                while (linea != null) {
                	String dniEmpleado = linea.substring(0, 9); // buscamos el DNI para comparar
                	if (dniEmpleado.equals(dni)) {
                		linea = linea.replaceAll("(?i)" + categoriaParaCambiar + ": [a-zA-Z_0-9]+", categoriaParaCambiar + ": " + valorNuevo);
                		
                	};
                	System.out.println(linea);
                	linea = br.readLine();
                	
                		
                	
              	
                	// eliminar la linea y escribir la nueva
 //               		pw.write(textoNuevo + System.getProperty("line.separator"));
 //               		pw.flush();
//                		System.out.println(linea);
					}
			
			} catch (IOException e) {
				System.out.println("No se puede mostrar el contenido. Se ha producido un error al leer del archivo");
			}
		} else {
			throw new IllegalStateException("El fichero no está abierto");
		}
	}
}
