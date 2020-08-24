package rentcarTest.conn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	public static Connection getConnection() {
		Connection conn = null;
		//String proptiesPath = "db.properties";
		// String proptiesPath = "mysql_db.properties";
		try {//(InputStream is = ClassLoader.getSystemResourceAsStream(proptiesPath)) {
			//Properties props = new Properties();
			//props.load(is);
			// System.out.println(props);
			//String url = props.getProperty("url");
			 //String user = props.getProperty("user");
			 //String password = props.getProperty("password");
			 String url = "jdbc:oracle:thin:@localhost:1521:orcl?useSSL=false";
			 String user = "RENT_TEST";
			 String password = "rootroot";
			// System.out.printf("user = %s, password = %s, url = %s%n", url, user,
			// password);
			conn = DriverManager.getConnection(url, user, password);
			//conn = DriverManager.getConnection(url, props);
		}
		//catch (IOException e) {
		//	System.out.println(e.getMessage());
		//	e.printStackTrace();
		//} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
}
