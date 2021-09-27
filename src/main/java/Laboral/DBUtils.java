package Laboral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/Laboral";
        String user = "root";
        String pass = "fa1ryHulkflus*";        
        
       // Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
    
    public static void close(Connection conn) throws SQLException {
        if (conn != null)
            conn.close();
    }
    
    public static void close(Statement st) throws SQLException {
        if (st != null)
            st.close();
    }
    
    public static void close(ResultSet rs) throws SQLException {
        if (rs != null)
            rs.close();
    }
    
}
