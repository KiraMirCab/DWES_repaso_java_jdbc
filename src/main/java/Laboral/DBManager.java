package Laboral;

import java.sql.*;

public class DBManager {
	
	Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    
	public void altaEmpleado(Empleado emp) {
		
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement("INSERT INTO EMP (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?)");
			st.setString(1, emp.dni);
            st.setString(2, emp.nombre);
            st.setString(3, String.valueOf(emp.sexo));
            st.setInt(4, emp.getCategoria());
            st.setInt(5, emp.anyos);
            
            int numFilas = st.executeUpdate();    
            System.out.println(numFilas+" filas insertadas en la tabla Empleados");
            
            st = conn.prepareStatement("INSERT INTO NOMINAS (dni, salario) VALUES (?, ?)");
			st.setString(1, emp.dni);
            st.setInt(2, Nomina.sueldo(emp));
            
		} catch(SQLException e) {
			System.out.println("Ocurrió algún error al conectar u operar con la BD");
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.out.println("Ocurrió algún error con el driver");
			e.printStackTrace();
		}
		finally {
		
		 try {
             DBUtils.close(rs);
             DBUtils.close(st);    
             DBUtils.close(conn);
         } catch(SQLException e) {
             System.out.println("Ocurrió una excepción al cerrar la BD");
         }
		}
	}

	
	public void UPDATE(String tableName) {
		
	}
	
	public void SELECT(String tableName) {
		
	}
}