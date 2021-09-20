package Laboral;

public class Persona {
	public String nombre;
	public String dni;
	public char sexo; //M (male) o F (female)
	
	//dos constructores:
	public Persona(String nombre, String dni, char sexo) {
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;		
	}

	public Persona(String nombre, char sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}

	//este método modifica el valor de dni de una persona:
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	//este método imprime el nombre y dni por pantalla:
	public void Imprime() {
		System.out.println(nombre + ", " + dni);
	}
	
	
}
