package com.bankapp.employee.service;

import com.bankapp.exception.BusinessException;

public interface EmployeeCRUDService {
	
	public void registerNewEmployee(String username, String password, String firstname, String lastname) throws BusinessException;

}
