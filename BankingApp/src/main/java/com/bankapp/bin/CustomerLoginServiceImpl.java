package com.bankapp.bin;

import java.util.List;

import org.apache.log4j.Logger;

import com.bankapp.exception.BusinessException;
import com.bankapp.main.menu.PrintMenu;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.Employee;
import com.bankapp.model.Transaction;
import com.bankapp.model.User;
import com.bankapp.user.customer.service.CustomerValidations;




public class CustomerLoginServiceImpl implements CustomerLoginService {
	private static Logger Log = Logger.getLogger(CustomerLoginServiceImpl.class);
	PrintMenu menu = new PrintMenu();

	CustomerLoginDao customerLogin = new CustomerLoginDAOImpl();
// user Customer Search
	@Override
	public User logIn(String username, String password) throws BusinessException {
		Log.info("username " + username + " password " + password); // to test bugs
		User user = null;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username)) {
			// code to dao
			user = customerLogin.logIn(username, password);
			Log.info(user + " from service after db has gone through");
			if (user != null) {
				if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
					Log.info("Loggin succesful and matches user name and password");
				} else {
					Log.info("Entered User name and password do not match our records");
					throw new BusinessException("Entered User name and password do not match our records");
				}

			} else {
				throw new BusinessException("Entered User name and password do not match our records");

			}
		} else {
//			menu.printCustomerLoginMenu();
			throw new BusinessException("Entered User name and password are invalid format");
		}
		return user;
	}
	// user service Crud
	@Override
	public void registerNewUser(String username, String password) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username)) {
			c = customerLogin.registerNewUser(username, password);
			if (c > 0) {
				Log.info("New account was registered succesfully");
			} else {
				Log.info("Unable to register the account");
			}
		} else {
			Log.info("User name and password are invalid format ");
		}

	}
	// user service CRUD
	@Override
	public int registerNewCustomer(String firstname, String lastname, String email, String phonenumber, String city,
			int age, String gender, int user_id) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		c = customerLogin.registerNewCustomer(firstname, lastname, email, phonenumber, city, age, gender, user_id);
		if (c > 0) {
			Log.info("New customer account was registered succesfully");
		} else {
			Log.info("Unable to register the account");
		}
		return c;
	}
	// user service Search
	@Override
	public int getUserId(String username) throws BusinessException {
		int id = 0;
		id = customerLogin.getUserId(username);
		if (id > 0) {
			Log.info("User id retrieved succesully");
		} else {
//			Log.info("Unable to retrieve id");
			throw new BusinessException("Unable to retrieve id");
		}
		return id;
	}
	// user service Search
	@Override
	public Customer getCustomer(int user_id) throws BusinessException {
		Customer customer = null;
		if (CustomerValidations.isValidUserId(user_id)) {
			customer = customerLogin.getCustomer(user_id);
			if (customer != null) {
				return customer;
			} else {
				throw new BusinessException("Unable to retrieve the Customer Class");
			}
		} else {
			return null;
		}
	}
// bank service Search
	@Override
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException {
		Log.info(customer_id);
		List<Account> accounts = customerLogin.getAllAccountsById(customer_id);
		if (accounts.size() == 0) {
			throw new BusinessException("No Accounts found for current customer ");
		}
		return accounts;
	}
	// bank service CRUD
	@Override
	public int createNewAccount(int customer_id, int deposit) throws BusinessException {
		int c = 0;
		try {
			c = customerLogin.createNewAccount(customer_id, deposit);
		} catch (BusinessException e) {

		}
		return c;
	}
	// bank service CRUD
	@Override
	public int depositIntoAccount(int account_id, double newBalance) throws BusinessException {
		int c = 0;
		if (newBalance < 0) {
			throw new BusinessException("The withdraw amount exceeds the balance");
		}
		try {
			c = customerLogin.setNewAccountBalance(account_id, newBalance);
		} catch (BusinessException e) {
			Log.info(e);
		}
		return c;
	}
	// bank service CRUD
	@Override
	public Account withdrawFromAccount(Account account, int withdraw, Transaction transaction) throws BusinessException {
		int c = 0;
		double balance = account.getBalance();
		double newBalance = balance - withdraw;
		if (newBalance < 0) {
			transaction.setStatus("Failure");
			c = customerLogin.recordTransaction(transaction);
			throw new BusinessException("The withdraw amount exceeds the balance");
		} else {
		try {
			c = customerLogin.setNewAccountBalance(account.getAccountid(), newBalance);
			account.setBalance(newBalance);
			Log.info("The withdraw was succesful");
			transaction.setStatus("Success");
			c = customerLogin.recordTransaction(transaction);
		} catch (BusinessException e) {
			Log.info(e);
		}
		}
		return account;
	}
// Employee service Search
	@Override
	public Employee employeelogIn(String username, String password) throws BusinessException {
		Log.info("username " + username + " password " + password); // to test bugs
		Employee employee = null;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username)) {
			employee = customerLogin.employeelogIn(username, password);
			Log.info(employee + " from service after db has gone through");
			if (employee != null) {
				Log.info("Employee has been succesfully retrieved");
			} else {
				throw new BusinessException("Entered User name and password do not match our records");

			}
		} else {
//			menu.printCustomerLoginMenu();
			throw new BusinessException("Entered User name and password are invalid format");
		}
		return employee;
	}
	// Employee service CRUD
	@Override
	public void registerNewEmployee(String username, String password, String firstname, String lastname) throws BusinessException {
		int c = 0;
		if (CustomerValidations.isValidPassword(password) && CustomerValidations.isValidUserName(username) && 
				CustomerValidations.isValidFirstName(firstname) && CustomerValidations.isValidLastName(lastname) ) {
			c = customerLogin.registerNewEmployee(username, password, firstname, lastname);
			if (c > 0) {
				Log.info("New account was registered succesfully");
			} else {
				Log.info("Unable to register the account");
				throw new BusinessException();
			}
		} else {
			Log.info("User name and password are invalid format ");
			throw new BusinessException();
		}
		
	}

// Bank service CRUD
	@Override
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException {
		List<Account> accounts = customerLogin.getAllAccountsByStatus(status);
		if (accounts.size() == 0) {
			throw new BusinessException("No Accounts found for current customer ");
		}
		return accounts;
	}
// User/Customer Service SEARCH
	@Override
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException {
		Customer customer = null;
		if (CustomerValidations.isValidCustomerId(customer_id)) {
			customer = customerLogin.getCustomerByCustomerId(customer_id);
			if (customer != null) {
				return customer;
			} else {
				throw new BusinessException("Unable to retrieve the Customer Class");
			}
		} else {
			return null;
		}
	}
// BankSerivice Search
	@Override
	public Account getAccountByAccountId(int account_id) throws BusinessException {
		Account account = null;
		account = customerLogin.getAccountByAccountId(account_id);
		if (account == null) {
			throw new BusinessException();
		}
		
		return account;
		
	} 
	// Bank service CRUD
	@Override
	public Account transferMoney(Account account, Account receivingAccount, double transaction_amount) throws BusinessException {
		double newAccountBalance = account.getBalance() - transaction_amount;
		double newReceivingAccountBalance = receivingAccount.getBalance() + transaction_amount ;
		if (newAccountBalance < 0 || newReceivingAccountBalance < 0 ) {
			throw new BusinessException("Transaction amount exceeds the account balance");
		}
		Log.info("The new balance is " + newAccountBalance);
		customerLogin.transferMoney(account.getAccountid(), receivingAccount.getAccountid(), newAccountBalance, newReceivingAccountBalance );
		account.setBalance(newAccountBalance);
		return account;
	}
	// Bank CRUD
	@Override
	public List<Transaction> getLogOfTransactions() throws BusinessException {
		List<Transaction> transactions = customerLogin.getLogOfTransactions();
		if (transactions.size() == 0) {
			throw new BusinessException("No transactions found ");
		}
		return transactions;
	}

}
