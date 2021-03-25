package com.bankapp.user.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bankapp.dao.dbutil.PostgresConnection;
import com.bankapp.exception.BusinessException;
import com.bankapp.user.customer.dao.CustomerCRUDDAO;


public class CustomerCRUDDAOImpl implements CustomerCRUDDAO {
	private static Logger Log = Logger.getLogger(CustomerCRUDDAOImpl.class);
	@Override
	public int registerNewUser(String username, String password) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_schema.users(username, passwords) values(?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Log.error("Internal Error"); 
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
		
		return c;
	}

	@Override
	public int registerNewCustomer(String firstname, String lastname, String email, String phonenumber, String city,
			int age, String gender, int user_id) throws BusinessException {
		
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_schema.customers(firstname,lastname, email, phonenumber, city,  age, gender,user_id) "
					+ "values(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, phonenumber);
			preparedStatement.setString(5, city);
			preparedStatement.setInt(6, age);
			preparedStatement.setString(7, gender);
			preparedStatement.setInt(8, user_id);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Log.error("Internal Error"); 
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
		
		return c;
	}

}
