package com.bankapp.employee.dao;

import com.bankapp.exception.BusinessException;

public interface EmployeeCRUDDAO {

	public int registerNewEmployee(String username, String password, String firstname, String lastname) throws BusinessException;
}
