package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Customer;


import utils.Constants;
import utils.DBFunction;

public class CustomerServices {

	public static String createCust(String custName, String custProfession) throws SQLException {
		
		
		PreparedStatement preparedStatement = null;
		
		JSONArray jsonResult = new JSONArray();
		JSONObject jsonResponse = new JSONObject();

		Connection conn = DBFunction.getDbConnection();
		conn.setAutoCommit(false);

		// Unique Id generation
		String custId = UUID.randomUUID().toString();

		var lsSql = " INSERT INTO CUSTOMER (CUST_NAME,CUST_PROFESSION,CUST_ID) " + " VALUES ('" + custName + "','"
				+ custProfession + "','" + custId + "') ";
		try {
			
			preparedStatement = conn.prepareStatement(lsSql);

			preparedStatement.executeUpdate();

			jsonResponse.put(Constants.STATUS, Constants.SUCCESS);
			jsonResponse.put(Constants.MESSAGE, "Customer register successfully");
			conn.commit();

		} catch (SQLException e) {

			jsonResponse.put(Constants.STATUS, Constants.FAILURE);
			jsonResponse.put(Constants.EXCEPTION, e.toString());

			conn.rollback();

		} catch (Exception e) {

			jsonResponse.put(Constants.STATUS, Constants.FAILURE);
			jsonResponse.put(Constants.EXCEPTION, e.toString());

			conn.rollback();

		}finally {
			preparedStatement.close();
			conn.close();
		}

		return jsonResponse.toString();
	}
	
	public static String showCust(String custName) {

//		Initialization
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Customer> users = new ArrayList<>();

		try {
			conn = DBFunction.getDbConnection();

			if (conn != null) {
//				String lsSql = "SELECT CUST_ID,CUST_NAME,CUST_PROFESSION FROM CUSTOMER WHERE CUST_NAME =?";
				String lsSql = " SELECT * FROM CUSTOMER where CUST_NAME = '"+custName+"'";
				
				System.out.println("lsSql " + lsSql);
				stmt = conn.prepareStatement(lsSql);
//				stmt.setString(1, custName);
				rs = stmt.executeQuery();

				while (rs.next()) {
					users.add(new Customer(rs.getString("CUST_ID"), rs.getString("CUST_NAME"),
							rs.getString("CUST_PROFESSION")));
				}
				System.out.println("hi");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in customer list " + e);

		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				System.out.println("Exception in customer list " + e);

			}
		}

		return users.toString();
	}

	public static String updateCutsomer(String custId, String custName, String custProfession) throws SQLException {
		PreparedStatement preparedStatement = null;

		Connection conn = DBFunction.getDbConnection();
		conn.setAutoCommit(false);

		JSONObject jsonResponse = new JSONObject();

		String lsSql = "UPDATE CUSTOMER SET CUST_NAME ='" + custName + "', CUST_PROFESSION = '" + custProfession
						+ "' WHERE CUST_ID = '" + custId + "'";

		try {
			preparedStatement = conn.prepareStatement(lsSql);

			int rowsAffected = preparedStatement.executeUpdate();

//			System.out.println("Rows Affected " + rowsAffected);
			if (rowsAffected > 0) {
				jsonResponse.put(Constants.STATUS, Constants.SUCCESS);
				jsonResponse.put(Constants.MESSAGE, "Customer Updated");

			} else {
				jsonResponse.put(Constants.STATUS, "Rows are not affected");
			}
			conn.commit();

		} catch (SQLException e) {

			jsonResponse.put(Constants.STATUS, Constants.FAILURE);
			jsonResponse.put(Constants.EXCEPTION, e.toString());

			conn.rollback();

		} finally {
			preparedStatement.close();
			conn.close();
		}
		return jsonResponse.toString();

	}

	public static String deleteCustomer(String custId) throws SQLException {

		Connection conn = DBFunction.getDbConnection();
		conn.setAutoCommit(false);

		PreparedStatement preparedStatement = null;

		JSONObject jsonResponse = new JSONObject();

		var lsSql = " DELETE FROM CUSTOMER WHERE CUST_ID = '" + custId + "'";
		try {
			preparedStatement = conn.prepareStatement(lsSql);

			preparedStatement.executeUpdate();

			jsonResponse.put(Constants.STATUS, Constants.SUCCESS);
			jsonResponse.put(Constants.MESSAGE, "Customer deleted successfully");
			conn.commit();

		} catch (Exception e) {

			jsonResponse.put(Constants.STATUS, Constants.FAILURE);
			jsonResponse.put(Constants.EXCEPTION, e.toString());
			conn.rollback();

		} finally {
			conn.close();
		}

		return jsonResponse.toString();
	}

	
}
