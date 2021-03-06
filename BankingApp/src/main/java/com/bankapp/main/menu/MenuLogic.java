package com.bankapp.main.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapp.bank.dao.BankCRUDDAO;
import com.bankapp.bank.dao.impl.BankCRUDDAOImpl;
import com.bankapp.bank.service.BankCRUDService;
import com.bankapp.bank.service.BankSearchService;
import com.bankapp.bank.service.impl.BankCRUDServiceImpl;
import com.bankapp.bank.service.impl.BankSearchServiceImpl;
import com.bankapp.employee.service.EmployeeCRUDService;
import com.bankapp.employee.service.EmployeeSearchService;
import com.bankapp.employee.service.impl.EmployeeCRUDServiceImpl;
import com.bankapp.employee.service.impl.EmployeeSearchServiceImpl;
import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.Employee;
import com.bankapp.model.Transaction;
import com.bankapp.model.User;
import com.bankapp.user.customer.dao.CustomerCRUDDAO;
import com.bankapp.user.customer.dao.impl.CustomerCRUDDAOImpl;
import com.bankapp.user.customer.service.CustomerCRUDService;
import com.bankapp.user.customer.service.CustomerSearchService;
import com.bankapp.user.customer.service.CustomerValidations;
import com.bankapp.user.customer.service.impl.CustomerCRUDServiceImpl;
import com.bankapp.user.customer.service.impl.CustomerSearchServiceImpl;

public class MenuLogic {
	private static Logger Log = Logger.getLogger(MenuLogic.class);
	PrintMenu menu = new PrintMenu();
	CustomerCRUDDAO customerCRUDdao = new CustomerCRUDDAOImpl();
	CustomerSearchService customerSearchService = new CustomerSearchServiceImpl();
	CustomerCRUDService customerCRUDService = new CustomerCRUDServiceImpl();
	BankSearchService bankSearchService = new BankSearchServiceImpl();
	BankCRUDService bankCRUDService = new BankCRUDServiceImpl();
	EmployeeCRUDService employeeCRUDService = new EmployeeCRUDServiceImpl();
	EmployeeSearchService employeeSearchService = new EmployeeSearchServiceImpl();
	BankCRUDDAO bankCRUDDAO = new BankCRUDDAOImpl();
	
	public User customerLogIn(Scanner scanner) throws BusinessException {
		User user = null;
		Log.info("Enter username: ");
		String username = scanner.nextLine();
		Log.info("Enter password: ");
		String password = (scanner.nextLine());
		user = customerSearchService.logIn(username, password);
		return user;
	}

	public void userRegistration(Scanner scanner) throws BusinessException {
		Log.info("Enter username: ");
		String username = scanner.nextLine();
		Log.info("Enter password: ");
		String password = (scanner.nextLine());
		try {
			customerCRUDService.registerNewUser(username, password);

		} catch (BusinessException e) {
			Log.info("Unable to register user");
			menu.printCustomerLoginMenu();
		}
	}

	public void Registration(Scanner scanner) throws BusinessException {
		Log.info("Enter username: ");
		String username = scanner.nextLine();
		Log.info("Enter password: ");
		String password = (scanner.nextLine());
		Log.info("Enter first name: ");
		String firstname = (scanner.nextLine());
		Log.info("Enter last name: ");
		String lastname = (scanner.nextLine());
		Log.info("Enter email ");
		String email = (scanner.nextLine());
		Log.info("Enter phoneNumber (6 digits) ");
		String phonenumber = (scanner.nextLine());
		Log.info("Enter city: ");
		String city = scanner.nextLine();
		Log.info("Enter age: ");
		int age = (Integer.parseInt(scanner.nextLine()));
		Log.info("Enter gender (m or f): ");
		String gender = scanner.nextLine();

		int user_id = 0;
		if (registrationValidation(username, password, firstname, lastname, email, phonenumber, city, age, gender)) {
			try {
				customerCRUDService.registerNewUser(username, password);
				user_id = customerSearchService.getUserId(username);
				customerCRUDService.registerNewCustomer(firstname, lastname, email, phonenumber, city, age, gender, user_id);

			} catch (BusinessException e) {
				Log.info("Unable to Register");
				menu.printCustomerLoginMenu();
			}
		} else {
			throw new BusinessException("Wrong format");
		}

	}

	public boolean registrationValidation(String username, String password, String firstname, String lastname,
			String email, String phoneNumber, String city, int age, String gender) {
		if (CustomerValidations.isValidUserName(username) && CustomerValidations.isValidUserName(username)
				&& CustomerValidations.isValidCity(city) && CustomerValidations.isValidFirstName(firstname)
				&& CustomerValidations.isValidLastName(lastname) && CustomerValidations.isValidPhoneNumber(phoneNumber)
				&& CustomerValidations.isValidAge(age) && CustomerValidations.isValidGender(gender)) {
			return true;
		} else {
			return false;
		}

	}
	
	public Customer getCustomerByUsername(String username) throws BusinessException {
		int user_id = 0;
		user_id = customerSearchService.getUserId(username);
		Customer customer = customerSearchService.getCustomer(user_id);

		return customer;
	}

	public List<Account> getAllAcountsById(int customer_id) {
		List<Account> accounts = new ArrayList<>();
		try {
		accounts = bankSearchService.getAllAccountsById(customer_id);
		} catch(BusinessException e) {
		}
		return accounts;
	}
	

	
	public List<Account> getAllPendingAcounts() {
		List<Account> accounts = new ArrayList<>();
		int status = 2;
		try {
		accounts = bankSearchService.getAllAccountsByStatus(status);
		} catch(BusinessException e) {
		Log.warn("No pending accounts found");
		}
		return accounts;
	}

	public void createNewAccount(int customer_id, Scanner scanner) throws BusinessException {
		int c=0;
		Log.info("Enter the initial deposit amount: ");
		int deposit = (Integer.parseInt(scanner.nextLine()));
		try {
			c = bankCRUDService.createNewAccount(customer_id , deposit);
		} catch (BusinessException e) {
			
		}
		if (c>0 ) {
			Log.info("Succesully created a new account");
		} else {
			throw new BusinessException("Unable to create new account");
		}
		
	}

	public Account depositIntoAccount(Scanner scanner, Account account, Customer customer) throws BusinessException {
		int c = 0;
		Log.info("Enter the  deposit amount: ");
		int deposit = (Integer.parseInt(scanner.nextLine()));
		double balance = account.getBalance();
		double newBalance = balance + deposit;
		
		
		Transaction transaction = new Transaction();
		transaction.setAccountid(account.getAccountid());
		transaction.setAmount(deposit);
		transaction.setCustomerid(customer.getCustomer_id());
		transaction.setTarget_id(0);

		transaction.setType(1);
		
		if (deposit < 0) {
			Log.error("Deposit ammount cannot be negative");
			transaction.setStatus("Failure");
			c = bankCRUDDAO.recordTransaction(transaction);
			throw new BusinessException("Unable to deposit the amount");
		}
		
			c = bankCRUDService.depositIntoAccount(account.getAccountid(), newBalance);
		if (c > 0) {
			Log.info("Successfully deposited money into your account");
			account.setBalance(newBalance);
			transaction.setStatus("Success");
			c = bankCRUDDAO.recordTransaction(transaction);

		} else {
			transaction.setStatus("Failure");
			c = bankCRUDDAO.recordTransaction(transaction);
			throw new BusinessException("Unable to deposit the amount");
		}
		return account;
		
	}

	public Account withdrawFromAccount(Scanner scanner, Account account, Customer customer) throws BusinessException {
		Log.info("Enter the  withdraw amount: ");
		int withdraw = (Integer.parseInt(scanner.nextLine()));
		int c=0;
		
		Transaction transaction = new Transaction();
		transaction.setAccountid(account.getAccountid());
		transaction.setAmount(withdraw);
		transaction.setCustomerid(customer.getCustomer_id());
		transaction.setTarget_id(0);
		transaction.setType(2);
		
		if (withdraw < 0) {
			Log.error("Withdraw amount should be a positive number");
			transaction.setStatus("Failure");
			c = bankCRUDDAO.recordTransaction(transaction);
			throw new BusinessException("Unable to withdraw the amount");
		}
			
		account = bankCRUDService.withdrawFromAccount(account, withdraw, transaction);
		return account;
	}

	public Employee EmpoyeeLogIn(Scanner scanner) throws BusinessException {
		Employee employee = null;
		Log.info("Enter username: ");
		String username = scanner.nextLine();
		Log.info("Enter password: ");
		String password = (scanner.nextLine());
		employee = employeeSearchService.employeelogIn(username, password);

		return employee;
	}

	public void createNewEmployeeAccount(Scanner scanner) throws BusinessException {
		Log.info("Enter username: ");
		String username = scanner.nextLine();
		Log.info("Enter password: ");
		String password = (scanner.nextLine());
		Log.info("Enter firstname: ");
		String firstname = (scanner.nextLine());
		Log.info("Enter lastname: ");
		String lastname = (scanner.nextLine());
		employeeCRUDService.registerNewEmployee(username, password,firstname,lastname);
	}

	public void approveAccount(int accountid) throws BusinessException {
		int c = 0;
		int status = 1;
		c = bankCRUDDAO.approveAccount(accountid, status);
		if (c<= 0) {
			throw new BusinessException();
		}
	}

	public void rejectAccount(int accountid) throws BusinessException {
		int c = 0;
		c = bankCRUDDAO.rejectAccount(accountid);
		if (c<= 0) {
			throw new BusinessException();
		}
		
	}

	public Customer getCustomerById(Scanner scanner) throws BusinessException {
		Log.info("Enter customer_id: ");
		int customer_id = Integer.parseInt(scanner.nextLine());
		Customer customer = customerSearchService.getCustomerByCustomerId(customer_id);
		return customer;
	}

	public Account transferMoney(Scanner scanner, Account account, Customer customer) throws BusinessException {
		Account receivingAccount = null;
		int c = 0;
		Log.info("Enter account id of the account you are transfering money to : ");
		int account_id = Integer.parseInt(scanner.nextLine());
		try {
			receivingAccount = bankSearchService.getAccountByAccountId(account_id);

		} catch (BusinessException e) {
			Log.warn("Unable to get the customer, maybe wrong id?");
			throw new BusinessException();
		}
		try {
		if (account.getAccountid() == receivingAccount.getAccountid()) {
			throw new BusinessException();
		}
		} catch (NullPointerException e) {
			
		}
		Log.info("Enter the transfer amount : ");
		double transaction_amount = Double.parseDouble(scanner.nextLine());

		Transaction transaction = new Transaction();
		transaction.setAccountid(account_id);
		transaction.setAmount(transaction_amount);
		transaction.setCustomerid(customer.getCustomer_id());
		transaction.setTarget_id(receivingAccount.getAccountid());
		int type = 3;
		transaction.setType(type);
		transaction.setStatus("Success");
		
		if (transaction_amount < 0) {
			Log.error("Cannot transfer negative amount");
			transaction.setStatus("Failure");
			c = bankCRUDDAO.recordTransaction(transaction);
			throw new BusinessException();
		}
		
		if (account.getBalance() - transaction_amount < 0) {
			Log.error("Cannot transfer more funds than  current balance");
			transaction.setStatus("Failure");
			c = bankCRUDDAO.recordTransaction(transaction);
			throw new BusinessException();
		}
		try{
		account = bankCRUDService.transferMoney(account, receivingAccount, transaction_amount);
		c = bankCRUDDAO.recordTransaction(transaction);
		} catch (BusinessException e) {
			Log.error("Transaction failure");
			transaction.setStatus("Failure");
			c = bankCRUDDAO.recordTransaction(transaction);
			
		}
		Log.info(c);
		
		
		return account;
		
		
	}

	public List<Transaction> LogOfTransactions() throws BusinessException {
		List<Transaction> transactions = new ArrayList<>();
		transactions = bankCRUDDAO.getLogOfTransactions();
		return transactions;
	}

}
