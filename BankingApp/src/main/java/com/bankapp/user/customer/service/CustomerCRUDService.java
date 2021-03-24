package com.bankapp.user.customer.service;

import com.bankapp.exception.BusinessException;

public interface CustomerCRUDService {

	public void registerNewUser(String username, String password) throws BusinessException;
	public int registerNewCustomer(String firstname, String lastname,
			String email, String phoneNumber, String city, int age, String gender, int user_id ) throws BusinessException; 
}
