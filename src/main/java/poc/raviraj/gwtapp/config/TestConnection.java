package poc.raviraj.gwtapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {

	public static void main(String[] args) {
		// Session s = HibernateUtil.getSession();
		// s.close();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4928/gwtpoc", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("show tables");
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
