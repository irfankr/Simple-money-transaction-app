package dao;
import java.sql.Connection;
import java.sql.DriverManager;

/**
* DBConnection klasa 
* za upravljanje konekcijom na bazi 
*/
public class DBConnection { 
	static String url 	   = new String("jdbc:mysql://localhost:3306/ssmkdb");
	static String username = new String("root");
	static String password = new String(""); //1234
	
	public static Connection getConnection() throws Exception {
		 	Class.forName("com.mysql.jdbc.Driver");
		 	Connection con = DriverManager.getConnection(url,username,password);
			return con;  
    }
}
