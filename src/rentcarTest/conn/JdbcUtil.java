package rentcarTest.conn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	public static Connection getConnection() {
		Connection con = null;
		String proptiesPath = "db.properties";
		try (InputStream is =Thread.currentThread().getContextClassLoader().getResourceAsStream(proptiesPath)){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Properties props = new Properties();
			props.load(is);
			
			String url = props.getProperty("url");
			
				/*String url = props.getProperty("url");
				String user = props.getProperty("user");
				String password = props.getProperty("password");*/
				
			con = DriverManager.getConnection(url, props);
				
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
