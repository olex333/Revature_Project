package com.bankapp.employee.service.impl;

import org.apache.log4j.Logger;

import com.bankapp.bin.CustomerLoginServiceImpl;
import com.bankapp.employee.dao.EmployeeCRUDDAO;
import com.bankapp.employee.dao.EmployeeSearchDAO;
import com.bankapp.employee.dao.impl.EmployeeCRUDDAOImpl;
import com.bankapp.employee.dao.impl.EmployeeSearchDAOImpl;
import com.bankapp.employee.service.EmployeeCRUDService;
import com.bankapp.exception.BusinessException;
import com.bankapp.user.customer.service.CustomerValidations;

public class EmployeeCRUDServiceImpl implements EmployeeCRUDService {
	private static Logger Log = Logger.getLogger(CustomerLoginServiceImpl.class);
	EmployeeCRUDDAO employeeCRUD = new EmployeeCRUDDAOImpl();
	
	@Override
	public void registerNewEmployee(String username, String password, String firstname, String lastname)
			throws BusinessException {
		int c = 0;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username) && 
				CustomerValidations.isValidFirstName(firstname) && CustomerValidations.isValidLastName(lastname) ) {
			c = employeeCRUD.registerNewEmployee(username, password, firstname, lastname);
			if (c > 0) {
				Log.info("New account was registered succesfully");
			} else {
				Log.info("Unable to register the account");
				throw new BusinessException();
			}
		} else {
			Log.info("User name and password are invalid format ");
			throw new BusinessException();
		}
		
	}

}
