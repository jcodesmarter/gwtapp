package poc.raviraj.gwtapp.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class JDBCConnectionTest {

	@Test
	public void test() {
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4928/gwtpoc", "root", "root");
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("show tables");
		rs.close();
		stmt.close();
		con.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
