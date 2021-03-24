package com.bankapp.employee.service;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Employee;

public interface EmployeeSearchService {
	
	public Employee employeelogIn(String username, String password) throws BusinessException;

}
