package com.bankapp.bin;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.Employee;
import com.bankapp.model.Transaction;
import com.bankapp.model.User;

public interface CustomerLoginDao {
	
	public User logIn(String username, String password) throws BusinessException; // user Customer Search
	public int registerNewUser(String username, String password) throws BusinessException; // user Customer service Crud
	public int getUserId(String username) throws BusinessException; // user Customer Search
	public int registerNewCustomer(String firstname, String lastname,
			String email, String phoneNumber, String city, int age, String gender, int user_id ) throws BusinessException;  // user Customer service Crud
	public Customer getCustomer(int user_id) throws BusinessException;  // user Customer Search
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException; // bank service Search
	public int createNewAccount(int customer_id, int deposit) throws BusinessException;  // bank service CRUD
	public int setNewAccountBalance(int account_id, double newBalance) throws BusinessException; // bank service CRUD
	public Employee employeelogIn(String username, String password) throws BusinessException;  //Employee service Search
	public int registerNewEmployee(String username, String password, String firstname, String lastname) throws BusinessException;  // Employee service CRUD
//	public List<Account> getAllActiveAccounts(int customer_id) throws BusinessException;
//	public List<Account> getAllPendingAccounts(int status) throws BusinessException;
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException; 	// Bank service search
	public int approveAccount(int accountid, int status) throws BusinessException; 		// Bank service CRUD
	public int rejectAccount(int accountid) throws BusinessException;  					// Bank service CRUD CRUD
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException; 		// user Customer Search
	public Account getAccountByAccountId(int account_id) throws BusinessException;			 // BankSerivice Search
	public void transferMoney(int accountid, int accountid2, double newAccountBalance, double newAccountBalance2) throws BusinessException;     // Bank service CRUD 
	public int recordTransaction(Transaction transaction ) throws BusinessException;   // Bank service CRUD
	public List<Transaction> getLogOfTransactions() throws BusinessException;			// Bank service CRUD
	




}
