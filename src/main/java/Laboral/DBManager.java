package Laboral;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

	Connection conn = null;
	PreparedStatement st, st2 = null;
	ResultSet rs = null;

	/**
	 * Este método convierte el contenido de la tabla Empleados en objetos
	 * @return array list de empleados
	 */
	public ArrayList<Empleado> leeTablaEmpleados() {
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement("SELECT dni, nombre, sexo, categoria, anyos FROM emp");
			rs = st.executeQuery();

			while (rs.next()) {
				String dni = rs.getString(1);
				String nombre = rs.getString(2);
				char sexo = rs.getString(3).charAt(0);
				int categoria = rs.getInt(4);
				int anyos = rs.getInt(5);
				Empleado emp = new Empleado(nombre, dni, sexo, categoria, anyos);
				empleados.add(emp);
			}
		} catch (SQLException e) {
			System.out.println("Ocurrió algún error al conectar u operar con la BD");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Ocurrió algún error con el driver");
			e.printStackTrace();
		} catch (DatosNoCorrectosException e) {
			System.out.println("Datos incorrectos, no se puede crear un empleado");
		} finally {
			try {
				DBUtils.close(rs);
				DBUtils.close(st);
				DBUtils.close(conn);
			} catch (SQLException e) {
				System.out.println("Ocurrió una excepción al cerrar la BD");
			}
		}
		return empleados;
	}

	/**
	 * Este método encuentra el empleado en la tabla por el dni y actualiza los datos del empleado
	 * en las dos tablas
	 * @param empleado
	 */
	public void actualizaEmpleado(Empleado emp) {
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement("UPDATE emp SET nombre = '" + emp.nombre + "', sexo = '" +
					emp.sexo + "', categoria = '" + emp.getCategoria() + "', anyos = '" + emp.anyos + 
					"' WHERE dni = '" + emp.getDNI() + "'");
			
			int numFilas = st.executeUpdate();
			System.out.println(numFilas + " filas de la tabla Empleado actualizadas");
			
			st = conn.prepareStatement("UPDATE nominas SET sueldo = '" + Nomina.sueldo(emp) + 
					"' WHERE dni = '" + emp.getDNI() + "'");
			
			numFilas = st.executeUpdate();
			System.out.println(numFilas + " filas de la tabla Nominas actualizadas");


		} catch (SQLException e) {
			System.out.println("Ocurrió algún error al conectar u operar con la BD");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Ocurrió algún error con el driver");
			e.printStackTrace();
		} finally {

			try {
				DBUtils.close(rs);
				DBUtils.close(st);
				DBUtils.close(conn);
			} catch (SQLException e) {
				System.out.println("Ocurrió una excepción al cerrar la BD");
			}
		}
	}
	
	/**
	 * Este método actualiza los sueldos de los empleados en la lista
	 * @param Empleado
	 */
	public void calculaSueldos (ArrayList<Empleado> empleados) {

		try {
			conn = DBUtils.getConnection();
			int numFilas = 0;
			st = conn.prepareStatement("INSERT IGNORE INTO nominas (dni, sueldo) VALUES (?, ?)");
			for (Empleado emp : empleados) {
				st.setString(1, emp.dni);
				st.setInt(2, Nomina.sueldo(emp));
				int insert = st.executeUpdate();
				numFilas += insert;
			}			

			System.out.println(numFilas + " filas insertadas en la tabla Nominas");

		} catch (SQLException e) {
			System.out.println("Ocurrió algún error al conectar u operar con la BD");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Ocurrió algún error con el driver");
			e.printStackTrace();
		} finally {

			try {
				DBUtils.close(rs);
				DBUtils.close(st);
				DBUtils.close(conn);
			} catch (SQLException e) {
				System.out.println("Ocurrió una excepción al cerrar la BD");
			}
		}
	}
	
	/**
	 * Este método inserta un nuevo empleado en la tabla de los datos de empleados e
	 * inserta su correspondiente salario en la tabla nominas
	 * 
	 * @param Empleado
	 */
	public void altaEmpleado(Empleado emp) {

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement("INSERT INTO emp (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)");
			st.setString(1, emp.dni);
			st.setString(2, emp.nombre);
			st.setString(3, String.valueOf(emp.sexo));
			st.setInt(4, emp.getCategoria());
			st.setInt(5, emp.anyos);

			int numFilas = st.executeUpdate();
			System.out.println(numFilas + " filas insertadas en la tabla Empleados");

			st = conn.prepareStatement("INSERT INTO nominas (dni, sueldo) VALUES (?, ?)");
			st.setString(1, emp.dni);
			st.setInt(2, Nomina.sueldo(emp));
			numFilas = st.executeUpdate();
			System.out.println(numFilas + " filas insertadas en la tabla Nominas");

		} catch (SQLException e) {
			System.out.println("Ocurrió algún error al conectar u operar con la BD");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Ocurrió algún error con el driver");
			e.printStackTrace();
		} finally {

			try {
				DBUtils.close(rs);
				DBUtils.close(st);
				DBUtils.close(conn);
			} catch (SQLException e) {
				System.out.println("Ocurrió una excepción al cerrar la BD");
			}
		}
	}
	
	/**
	 * El mismo que altaEmpleado pero recibe un array de empleados 
	 * @param array list de empleados
	 */
	public void altaEmpleado(ArrayList<Empleado> empleados) {
		for (Empleado empleado : empleados) {
			altaEmpleado(empleado);
		}
	}

		
}
