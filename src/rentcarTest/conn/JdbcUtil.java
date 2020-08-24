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
		String propertiesPath = "db.properties";
		try (InputStream is = ClassLoader.getSystemResourceAsStream(propertiesPath)) {
			Properties props = new Properties();
			props.load(is);

			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("password");

			con = DriverManager.getConnection(url, props);

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
