package com.bankapp.employee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bankapp.dao.dbutil.PostgresConnection;
import com.bankapp.employee.dao.EmployeeSearchDAO;
import com.bankapp.exception.BusinessException;
import com.bankapp.model.Employee;

public class EmployeeSearchDAOImpl implements EmployeeSearchDAO {
	private static Logger Log = Logger.getLogger(EmployeeSearchDAOImpl.class);

	@Override
	public Employee employeelogIn(String username, String password) throws BusinessException {
		Employee employee = null;

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select username, password, firstname, lastname, employee_id from banking_schema.employees where username=? and password =? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee();
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setFirstName(resultSet.getString("firstname"));
				employee.setLastName(resultSet.getString("lastname"));
				employee.setEmployee_id(resultSet.getString("employee_id"));

			}
		} catch (ClassNotFoundException | SQLException e) {

			Log.warn("Internal Error");
			throw new BusinessException("Internal Error");
		}

		return employee;
	}

}
