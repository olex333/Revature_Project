package com.bankapp.user.customer.service.impl;

import org.apache.log4j.Logger;

import com.bankapp.exception.BusinessException;
import com.bankapp.user.customer.dao.CustomerCRUDDAO;
import com.bankapp.user.customer.dao.impl.CustomerCRUDDAOImpl;
import com.bankapp.user.customer.service.CustomerCRUDService;
import com.bankapp.user.customer.service.CustomerValidations;

public class CustomerCRUDServiceImpl implements CustomerCRUDService {
	private static Logger Log = Logger.getLogger(CustomerCRUDServiceImpl.class);
	CustomerCRUDDAO customerCRUD = new CustomerCRUDDAOImpl();

	@Override
	public void registerNewUser(String username, String password) throws BusinessException {
		int c = 0;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username)) {
			c = customerCRUD.registerNewUser(username, password);
			if (c > 0) {
				Log.info("New account was registered succesfully");
			} else {
				Log.info("Unable to register the account");
			}
		} else {
			Log.info("User name and password are invalid format ");
		}
	}

	@Override
	public int registerNewCustomer(String firstname, String lastname, String email, String phonenumber, String city,
			int age, String gender, int user_id) throws BusinessException {
		int c = 0;
		c = customerCRUD.registerNewCustomer(firstname, lastname, email, phonenumber, city, age, gender, user_id);
		if (c > 0) {
			Log.info("New customer account was registered succesfully");
		} else {
			Log.info("Unable to register the account");
		}
		return c;
	}

}
