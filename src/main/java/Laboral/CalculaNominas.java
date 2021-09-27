package Laboral;

import java.io.File;

public class CalculaNominas {

	public static void main(String[] args) {
		try {
			//Creamos los objetos de empleados y el fichero donde los guardaremos:
			Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
			Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
			
			Fichero f = new Fichero("empleados.txt");
			f.abrir();
			
			//grabamos los datos de los empleados en el fichero y los visualizamos:
			f.escribeTexto(e1.toString());
			f.escribeTexto(e2.toString());
			f.imprimeContenido();
			
			//cambiamos la propiedad "Categoría" en el objeto:
			e1.setCategoria(9);
			
			//convertimos el valor de la propiedad de int a String,
				//actualizamos los datos en el archivo y visualizamos el fichero con los datos nuevos:
			String valorCategoria = Integer.toString(e1.getCategoria());
			f.actualizarFichero(e1.getDNI(), "Categoria", valorCategoria); 
			f.imprimeContenido();
			f.cerrar();
			
			//creamos el fichero de salarios:
			Fichero f1 = new Fichero("salarios.dat");
			f1.abrir();
			
			//grabamos los dni de los empleados y sus salarios en el fichero y lo visualizamos:
			f1.escribeTexto(e1.getDNI() + " " + Integer.toString(Nomina.sueldo(e1)));
			f1.escribeTexto(e2.getDNI() + " " + Integer.toString(Nomina.sueldo(e2)));
			f1.imprimeContenido();
			f1.cerrar();

			DBManager db = new DBManager();
			db.altaEmpleado(e2);
			
			/*escribe(e1);
			escribe(e2);
			e1.setCategoria(9);
			escribe(e1);
			escribe(e2);*/
		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos no correctos");
		}		
	}
	
	private static void escribe(Empleado emp) {
		emp.Imprime();
		System.out.println(Nomina.sueldo(emp));
	}

}
