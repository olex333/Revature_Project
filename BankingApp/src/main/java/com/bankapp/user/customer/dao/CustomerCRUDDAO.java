package com.bankapp.user.customer.dao;

import com.bankapp.exception.BusinessException;

public interface CustomerCRUDDAO {
	
	public int registerNewUser(String username, String password) throws BusinessException;
	public int registerNewCustomer(String firstname, String lastname,
			String email, String phonenumber, String city, int age, String gender, int user_id ) throws BusinessException;
}
