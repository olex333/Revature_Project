package com.bankapp.bin;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.Employee;
import com.bankapp.model.Transaction;
import com.bankapp.model.User;

public interface CustomerLoginService {
	
	public User logIn(String username, String password) throws BusinessException;  // user Customer Search
	public void registerNewUser(String username, String password) throws BusinessException;  // user Customer service Crud
	public int registerNewCustomer(String firstname, String lastname,
			String email, String phoneNumber, String city, int age, String gender, int id ) throws BusinessException; // user Customer service Crud
	public int getUserId(String username) throws BusinessException; // user Customer Search
	public Customer getCustomer(int user_id) throws BusinessException; // user Customer Search
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException; 			// bank service Search
	public int createNewAccount(int customer_id, int deposit) throws BusinessException; 		// bank service CRUD
	public int depositIntoAccount(int accountid, double newBalance) throws BusinessException;	// bank service CRUD
	public Account withdrawFromAccount(Account account, int withdraw, Transaction transaction) throws BusinessException;  // bank service CRUD
	public Employee employeelogIn(String username, String password) throws BusinessException;			//Employee service Search
	public void registerNewEmployee(String username, String password, String firstname, String lastname) throws BusinessException;  // Employee service CRUD
//	public List<Account> getAllActiveAccounts(int customer_id, int accountstatus) throws BusinessException;
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException;			// Bank service search
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException; 			// user Customer Search
	public Account getAccountByAccountId(int account_id) throws BusinessException; 				 // BankSerivice Search
	public Account transferMoney(Account account, Account receivingAccount, double transaction_amount) throws BusinessException;      // Bank service CRUD
	public List<Transaction> getLogOfTransactions() throws BusinessException;  // Bank service CRUD



}
