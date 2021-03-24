package com.bankapp.user.customer.service;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.Employee;
import com.bankapp.model.Transaction;
import com.bankapp.model.User;

public interface CustomerLoginService {
	
	public User logIn(String username, String password) throws BusinessException;
	public void registerNewUser(String username, String password) throws BusinessException;
	public int registerNewCustomer(String firstname, String lastname,
			String email, String phoneNumber, String city, int age, String gender, int id ) throws BusinessException;
	public int getUserId(String username) throws BusinessException;
	public Customer getCustomer(int user_id) throws BusinessException;
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException;
	public int createNewAccount(int customer_id, int deposit) throws BusinessException;
	public int depositIntoAccount(int accountid, double newBalance) throws BusinessException;
	public Account withdrawFromAccount(Account account, int withdraw, Transaction transaction) throws BusinessException;
	public Employee employeelogIn(String username, String password) throws BusinessException;
	public void registerNewEmployee(String username, String password, String firstname, String lastname) throws BusinessException;
//	public List<Account> getAllActiveAccounts(int customer_id, int accountstatus) throws BusinessException;
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException;
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException;
	public Account getAccountByAccountId(int account_id) throws BusinessException;
	public Account transferMoney(Account account, Account receivingAccount, double transaction_amount) throws BusinessException;
	public List<Transaction> getLogOfTransactions() throws BusinessException;



}
