package Laboral;

import java.io.File;
import java.util.ArrayList;

public class CalculaNominas {

	public static void main(String[] args) {
		try {
			/* Parte 2
			 * 1.1. Definir el fichero de texto de entrada “empleados.txt” creando en el mismos
			 * los empleados de los apartados 4.1 y 4.2 con el formato más adecuado para
			 * que pueda ser leído por el programa. */
			
			//Creamos los empleados
			Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
			Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
			
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
			
			/* 
			 * 1.2. Actualizar dicho fichero “empleados.txt” conforme a los cambios especificados
			 * en el apartado 4.5: Incremente los años trabajados del segundo empleado y haga 
			 * que la categoría del primero sea 9*/
			
			//actualizamos los datos de los empleados y los añadimos a un array:
			e2.incrAnyo();
			e1.setCategoria(9);
			ArrayList<Empleado> empleadosaEscribir = new ArrayList<Empleado>();
			empleadosaEscribir.add(e1);
			empleadosaEscribir.add(e2);
			
			//actualizamos la linea en el fichero y visualizamos el fichero con los datos nuevos:
			f.actualizarFichero(empleadosaEscribir);
			f.imprimeContenido();
			
			
			/*
			 * 1.3. Definir el fichero binario de salida “sueldos.dat” con el formato más adecuado
			 * para almacenar el dni y el sueldo resultante para cada empleado.*/
			
			//creamos el fichero de salarios:
			Fichero f1 = new Fichero("salarios.dat");
			f1.abrir();
			
			//grabamos los dni de los empleados que hemos extraido, calculamos y grabamos el salario
				//y lo visualizamos:
			for (Empleado empleado : empleadosExtraidos) {
				f1.escribeTexto(empleado.getDNI() + " " + Integer.toString(Nomina.sueldo(empleado)));
			}
			f1.imprimeContenido();
			f1.cerrar();
			f.cerrar();
			
		
			/* 
			 * 2.1. Definir e implementar el modelo de tablas y relaciones necesarios para dar 
			 * soporte a la aplicación de control de nóminas. Definir en la tabla correspondiente
			 * de dicha base de datos los mismos los empleados de los apartados 4.1 y 4.2 de
			 * la parte 1 para que pueda ser leído por el programa. Puedes incluir más registros
			 * de empleados.*/
			
			//Inicializamos el manejador de bases de datos, leemos el contedido de la tabla empleados
				//y lo mostramos por consola:
			DBManager db = new DBManager();
			ArrayList<Empleado> empleados = db.leeTablaEmpleados();				
			for (Empleado empleado : empleados) {
				System.out.println(empleado.toString());
			}
			
			/*
			 * 2.2. Actualizar dicha base de datos conforme a los cambios especificados en el
			 * apartado 4.5 de la parte 1: Incremente los años trabajados del segundo empleado y haga 
			 * que la categoría del primero sea 9.*/
			
			db.actualizaEmpleado(e1);
			db.actualizaEmpleado(e2);
			
			/*
			 * 2.3. Actualizar la base de datos almacenando el sueldo resultante para cada empleado.*/
			empleados = db.leeTablaEmpleados();
			for (Empleado empleado : empleados) {
				System.out.println(empleado.toString());
			}
			db.calculaSueldos(empleados);
			
			/*
			 * 3. Modificar el código para crear un método “altaEmpleado” que permita dar de alta
			 * empleados en el sistema y que, de forma automática, calcule y almacene el sueldo
			 * de los empleados en la base de datos.*/
			
			Empleado e3 = new Empleado("Tomate Macizo", "85425090D", 'M', 6, 3);
			//db.altaEmpleado(e3);
			
			/*
			 * 3.1. Sobrecargar el método “altaEmpleado” para que permita el alta de empleados
			 * de forma individual o por lotes a partir de un fichero “empleadosNuevos.txt”
			 * con los datos de los empleados a dar de alta en el sistema.*/
			
			//escribimos los datos de nuevos empleados en empleadosNuevos.txt
			Empleado e4 = new Empleado("Zanahoria Estupenda", "85455090D", 'F', 6, 3);
			Empleado e5 = new Empleado("Remolacha Guapa", "85425890D", 'F', 1, 2);
			
			Fichero f2 = new Fichero("empleadosNuevos.txt");
			f2.abrir();
			f2.escribeTexto(e4.toString());
			f2.escribeTexto(e5.toString());

			//leemos el fichero y extraemos los empleados en un array que a su vez pasamos a 
				//persistir en la base de datos
			empleados = f2.leerFicheroEmpleados();
			f2.cerrar();
			
			for (Empleado empleado : empleados) {
				System.out.println(empleado);
			}
			db.altaEmpleado(empleados);
			
		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos no correctos");
		}		
	}
	
	private static void escribe(Empleado emp) {
		emp.Imprime();
		System.out.println(Nomina.sueldo(emp));
	}

}
