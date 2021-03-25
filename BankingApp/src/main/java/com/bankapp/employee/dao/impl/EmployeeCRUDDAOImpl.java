package com.bankapp.employee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bankapp.dao.dbutil.PostgresConnection;
import com.bankapp.employee.dao.EmployeeCRUDDAO;
import com.bankapp.exception.BusinessException;

import jdk.internal.org.jline.utils.Log;

public class EmployeeCRUDDAOImpl implements EmployeeCRUDDAO {
	private static Logger Log = Logger.getLogger(EmployeeCRUDDAOImpl.class);

	@Override
	public int registerNewEmployee(String username, String password, String firstname, String lastname)
			throws BusinessException {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into banking_schema.employees(username, password, firstname, lastname) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstname);
			preparedStatement.setString(4, lastname);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Log.warn("Internal error");
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}

		return c;
	}

}
