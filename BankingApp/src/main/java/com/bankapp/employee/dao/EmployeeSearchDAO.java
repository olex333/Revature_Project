package com.bankapp.employee.dao;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Employee;

public interface EmployeeSearchDAO {
	
	public Employee employeelogIn(String username, String password) throws BusinessException;

}
