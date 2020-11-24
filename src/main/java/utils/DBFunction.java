package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBFunction {

	public static Connection getDbConnection() {
		System.out.println("Inside database");
		Connection conn = null;

		try {
			// step1 load the driver class
			Class.forName(Constants.DRIVER);

			// step2 create the connection object
			conn = DriverManager.getConnection(Constants.DRIVER_URL, Constants.USERNAME, Constants.PASSWORD);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

}
