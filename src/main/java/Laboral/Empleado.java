package Laboral;

public class Empleado extends Persona {
	private int categoria;
	public int anyos;
	
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		
		if (categoria >=1 && categoria <=10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("la categría debe estar entre 1 y 10");
		}
		
		if (anyos >= 0) {
			this.anyos = anyos;
		} else {
			throw new DatosNoCorrectosException("la categría debe estar entre 1 y 10");
		}		
	}

	public Empleado(String nombre, String dni, char sexo) {
		super(nombre, dni, sexo);
		this.categoria = 1;
		this.anyos = 0;
	}

	public int getCategoria() {
		return categoria;
	}
	
	public String getDNI() {
		return dni;
	}

	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria >=1 && categoria <=10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("la categría debe estar entre 1 y 10");
		}	
	}
	
	//este método incrementa el valor de anyos en 1:
	public void incrAnyo() {	
		this.anyos++;
	}
	
	//este método imprime todos los datos del empleado:
	public void Imprime() {
		System.out.println(nombre + ", " + dni + ", Sexo: " + sexo + ", Categoría: " + categoria + ", Edad: " + anyos);
	}

	public String toString() {
		return nombre + ", " + dni + ", " + sexo + ", " + categoria + ", " + anyos;
	}
	
	

}
