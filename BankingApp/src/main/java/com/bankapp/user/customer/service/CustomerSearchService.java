package com.bankapp.user.customer.service;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.User;

public interface CustomerSearchService {
	
	public User logIn(String username, String password) throws BusinessException;
	public int getUserId(String username) throws BusinessException;
	public Customer getCustomer(int user_id) throws BusinessException;
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException;

	

}
