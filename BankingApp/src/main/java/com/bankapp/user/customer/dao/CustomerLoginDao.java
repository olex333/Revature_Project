package com.bankapp.user.customer.dao;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.Employee;
import com.bankapp.model.Transaction;
import com.bankapp.model.User;

public interface CustomerLoginDao {
	
	public User logIn(String username, String password) throws BusinessException;
	public int registerNewUser(String username, String password) throws BusinessException;
	public int getUserId(String username) throws BusinessException;
	public int registerNewCustomer(String firstname, String lastname,
			String email, String phoneNumber, String city, int age, String gender, int user_id ) throws BusinessException;
	public Customer getCustomer(int user_id) throws BusinessException;
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException;
	public int createNewAccount(int customer_id, int deposit) throws BusinessException;
	public int setNewAccountBalance(int account_id, double newBalance) throws BusinessException;
	public Employee employeelogIn(String username, String password) throws BusinessException;
	public int registerNewEmployee(String username, String password, String firstname, String lastname) throws BusinessException;
//	public List<Account> getAllActiveAccounts(int customer_id) throws BusinessException;
//	public List<Account> getAllPendingAccounts(int status) throws BusinessException;
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException;
	public int approveAccount(int accountid, int status) throws BusinessException;
	public int rejectAccount(int accountid) throws BusinessException;
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException;
	public Account getAccountByAccountId(int account_id) throws BusinessException;
	public void transferMoney(int accountid, int accountid2, double newAccountBalance, double newAccountBalance2) throws BusinessException;
	public int recordTransaction(Transaction transaction ) throws BusinessException;
	public List<Transaction> getLogOfTransactions() throws BusinessException;
	




}
