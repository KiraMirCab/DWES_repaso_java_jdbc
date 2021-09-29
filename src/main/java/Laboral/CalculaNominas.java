package Laboral;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class CalculaNominas {

	public static void main(String[] args) {
		try {
			//Creamos los empleados y calculamos sus nominas (correspode a la Parte 1)
			Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
			Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
			
			escribe(e1);
			escribe(e2);
			e1.setCategoria(9);
			escribe(e1);
			
			//Creamos el fichero para guardar los empleados:
			Fichero f = new Fichero("empleados.txt");
			f.abrir();
			
			//grabamos los datos de los empleados en el fichero y los visualizamos:
			f.escribeTexto(e1.toString());
			f.escribeTexto(e2.toString());
			f.imprimeContenido();
			
		
			//leemos el fichero de empleados y mostramos por consola las propiedades
				//de los nuevos objetos sacados del fichero
			ArrayList<Empleado> empleadosExtraidos = f.leerFicheroEmpleados();
			for (Empleado empleado : empleadosExtraidos) {
				System.out.println(empleado.toString());
			}
			
			//creamos el fichero de salarios:
			Fichero f1 = new Fichero("salarios.dat");
			f1.abrir();
			
			//grabamos los dni de los empleados que hemos extraido, calculamos y grabamos el salario
				//y lo visualizamos:
			for (Empleado empleado : empleadosExtraidos) {
				f1.escribeTexto(empleado.getDNI() + " " + " " + Integer.toString(Nomina.sueldo(empleado)));
			}
			f1.imprimeContenido();
			f1.cerrar();
			f.cerrar();
			
		
			
			/*
			 * esta parte hay que arreglarla todavía* //cambiamos la propiedad "Categoría" en el objeto:
			e1.setCategoria(9);
			
			//convertimos el valor de la propiedad de int a String,
				//actualizamos los datos en el archivo y visualizamos el fichero con los datos nuevos:
			String valorCategoria = Integer.toString(e1.getCategoria());
			//f.actualizarFichero(e1.getDNI(), "Categoria", valorCategoria); 
			f.imprimeContenido();*/
		
			
			//Inicializamos el manejador de bases de datos y damos de alta un nuevo empleado
			DBManager db = new DBManager();
			Empleado e3 = new Empleado("Patata Gómez", "32000090Q", 'F');
			db.altaEmpleado(e3);
			
		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos no correctos");
		}		
	}
	
	private static void escribe(Empleado emp) {
		emp.Imprime();
		System.out.println(Nomina.sueldo(emp));
	}

}
