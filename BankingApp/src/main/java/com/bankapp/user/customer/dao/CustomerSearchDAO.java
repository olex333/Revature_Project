package com.bankapp.user.customer.dao;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Customer;
import com.bankapp.model.User;

public interface CustomerSearchDAO {
	public User logIn(String username, String password) throws BusinessException;
	public int getUserId(String username) throws BusinessException;
	public Customer getCustomer(int user_id) throws BusinessException;
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException;

}
