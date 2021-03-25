package com.bankapp.user.customer.service.impl;

import org.apache.log4j.Logger;

import com.bankapp.bin.CustomerLoginServiceImpl;
import com.bankapp.exception.BusinessException;
import com.bankapp.model.Customer;
import com.bankapp.model.User;
import com.bankapp.user.customer.dao.CustomerSearchDAO;
import com.bankapp.user.customer.dao.impl.CustomerSearchDAOImpl;
import com.bankapp.user.customer.service.CustomerSearchService;
import com.bankapp.user.customer.service.CustomerValidations;

public class CustomerSearchServiceImpl implements CustomerSearchService {
	private static Logger Log = Logger.getLogger(CustomerLoginServiceImpl.class);
	CustomerSearchDAO customerSearch = new CustomerSearchDAOImpl();

	public User logIn(String username, String password) throws BusinessException {
		User user = null;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username)) {
			user = customerSearch.logIn(username, password);
			if (user != null) {
				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
					Log.info("Loggin succesful");
				} else {
					Log.info("Entered User name and password do not match our records");
					throw new BusinessException("Entered User name and password do not match our records");
				}

			} else {
				throw new BusinessException("Entered User name and password do not match our records");

			}
		} else {
//			menu.printCustomerLoginMenu();
			throw new BusinessException("Entered User name and password are invalid format");
		}
		return user;
		
		
		
	}
	
	@Override
	public int getUserId(String username) throws BusinessException {
		int id = 0;
		id = customerSearch.getUserId(username);
		if (id <= 0) {
			throw new BusinessException("Unable to retrieve id");
		}
		return id;
	}
	
	public Customer getCustomer(int user_id) throws BusinessException {
		Customer customer = null;
		if (CustomerValidations.isValidUserId(user_id)) {
			customer = customerSearch.getCustomer(user_id);
			if (customer != null) {
				return customer;
			} else {
				throw new BusinessException("Unable to retrieve the Customer");
			}
		} else {
			return null;
		}
	}
	
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException {
		Customer customer = null;
		if (CustomerValidations.isValidCustomerId(customer_id)) {
			customer = customerSearch.getCustomerByCustomerId(customer_id);
			if (customer != null) {
				return customer;
			} else {
				throw new BusinessException("Unable to retrieve the Customer Class");
			}
		} else {
			return null;
		}
	}
}
