package com.bankapp.user.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bankapp.dao.dbutil.PostgresConnection;
import com.bankapp.exception.BusinessException;
import com.bankapp.model.Customer;
import com.bankapp.model.User;
import com.bankapp.user.customer.dao.CustomerSearchDAO;

public class CustomerSearchDAOImpl implements  CustomerSearchDAO {
	private static Logger Log = Logger.getLogger(CustomerSearchDAOImpl.class);
	
	@Override
	public User logIn(String username, String password) throws BusinessException {
		User user = null;
	
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select username, passwords from banking_schema.users where username=? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("passwords"));
			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
//				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
//		Log.info(user);
			
			
		return user;
	}
	
	@Override
	public int getUserId(String username) throws BusinessException {
		int id = 0;
		
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select user_id from banking_schema.users where username=? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt("user_id");
			}
			} catch (ClassNotFoundException | SQLException e) {
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}

			
			
		return id;
	}
	
	@Override
	public Customer getCustomer(int user_id) throws BusinessException {
		Customer customer = null;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select firstname,lastname, email, phonenumber, city,  age, gender, customerstatus, user_id, customer_id from banking_schema.customers where user_id =? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer = new Customer();
				customer.setAge(resultSet.getInt("age"));
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhoneNumber(resultSet.getString("phonenumber"));
				customer.setCity(resultSet.getString("city"));
				customer.setCustomerStatus(resultSet.getString("firstname"));
				customer.setGender(resultSet.getString("gender"));
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setCustomer_id(resultSet.getInt("customer_id"));


			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
//				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
//		Log.info(customer);
			
			
		return customer;
	}
	
	@Override
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException {
		Customer customer = null;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select firstname,lastname, email, phonenumber, city,  age, gender, customerstatus, user_id, customer_id from banking_schema.customers where customer_id =? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer_id);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer = new Customer();
				customer.setAge(resultSet.getInt("age"));
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhoneNumber(resultSet.getString("phonenumber"));
				customer.setCity(resultSet.getString("city"));
				customer.setCustomerStatus(resultSet.getString("firstname"));
				customer.setGender(resultSet.getString("gender"));
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setCustomer_id(resultSet.getInt("customer_id"));


			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
			
			
		return customer;
	}

}
