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
//	CustomerLoginDao customerLogin = new CustomerLoginDAOImpl();
	EmployeeSearchDAO employeeSearch = new EmployeeSearchDAOImpl();
	@Override
	public Employee employeelogIn(String username, String password) throws BusinessException {
//		Log.info("username " + username + " password " + password); // to test bugs
		Employee employee = null;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username)) {
			employee = employeeSearch.employeelogIn(username, password);
			Log.info(employee + " from service after db has gone through");
			if (employee != null) {
				Log.info("Employee has been succesfully retrieved");
			} else {
				throw new BusinessException("Entered User name and password do not match our records");

			}
		} else {
//			menu.printCustomerLoginMenu();
			throw new BusinessException("Entered User name and password are invalid format");
		}
		return employee;
	}

}
