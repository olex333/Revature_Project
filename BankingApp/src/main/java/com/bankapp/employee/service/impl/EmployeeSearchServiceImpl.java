package com.bankapp.employee.service.impl;

import org.apache.log4j.Logger;

import com.bankapp.bin.CustomerLoginDAOImpl;
import com.bankapp.bin.CustomerLoginDao;
import com.bankapp.bin.CustomerLoginServiceImpl;
import com.bankapp.employee.dao.EmployeeSearchDAO;
import com.bankapp.employee.dao.impl.EmployeeSearchDAOImpl;
import com.bankapp.employee.service.EmployeeSearchService;
import com.bankapp.exception.BusinessException;
import com.bankapp.model.Employee;
import com.bankapp.user.customer.service.CustomerValidations;

public class EmployeeSearchServiceImpl implements EmployeeSearchService {
	private static Logger Log = Logger.getLogger(CustomerLoginServiceImpl.class);
	EmployeeSearchDAO employeeSearch = new EmployeeSearchDAOImpl();
	
	@Override
	public Employee employeelogIn(String username, String password) throws BusinessException {

		Employee employee = null;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username)) {
			employee = employeeSearch.employeelogIn(username, password);
			if (employee != null) {
				Log.info("Employee has been succesfully retrieved");
			} else {
				throw new BusinessException("Entered User name and password do not match our records");

			}
		} else {
			throw new BusinessException("Entered User name and password are invalid format");
		}
		return employee;
	}

}
