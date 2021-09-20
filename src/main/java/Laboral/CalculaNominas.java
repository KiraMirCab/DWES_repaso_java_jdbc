package Laboral;

public class CalculaNominas {

	public static void main(String[] args) {
		try {
			Empleado e1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
			Empleado e2 = new Empleado("Ada Lovelace", "32000031R", 'F');
			
			escribe(e1);
			escribe(e2);
			e1.setCategoria(9);
			escribe(e1);
			escribe(e2);
		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos no correctos");
		}
		
	}
	
	private static void escribe(Empleado emp) {
		emp.Imprime();
		System.out.println(Nomina.sueldo(emp));
	}

}
