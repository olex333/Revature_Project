package com.bankapp.user.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankapp.dao.dbutil.PostgresConnection;
import com.bankapp.exception.BusinessException;
import com.bankapp.main.Main;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.Employee;
import com.bankapp.model.Transaction;
import com.bankapp.model.User;





public class CustomerLoginDAOImpl implements CustomerLoginDao {
	private static Logger Log = Logger.getLogger(CustomerLoginDAOImpl.class);


	@Override
	public User logIn(String username, String password) throws BusinessException {
		User user = null;
	
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select username, passwords from banking_schema.users where username=? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("passwords"));
			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
		Log.info(user);
			
			
		return user;
	}


	@Override
	public int registerNewUser(String username, String password) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_schema.users(username, passwords) values(?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //only at development not at production/live
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
		
		return c;
	}


	@Override
	public int getUserId(String username) throws BusinessException {
		int id = 0;
		
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select user_id from banking_schema.users where username=? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt("user_id");
			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
		Log.info(id);
			
			
		return id;
	}


	@Override
	public int registerNewCustomer(String firstname, String lastname, String email, String phonenumber, String city,
			int age, String gender, int user_id) throws BusinessException {
				
				int c=0;
				try(Connection connection=PostgresConnection.getConnection()){
					String sql = "insert into banking_schema.customers(firstname,lastname, email, phonenumber, city,  age, gender,user_id) "
							+ "values(?,?,?,?,?,?,?,?)";
					PreparedStatement preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setString(1, firstname);
					preparedStatement.setString(2, lastname);
					preparedStatement.setString(3, email);
					preparedStatement.setString(4, phonenumber);
					preparedStatement.setString(5, city);
					preparedStatement.setInt(6, age);
					preparedStatement.setString(7, gender);
					preparedStatement.setInt(8, user_id);
					c=preparedStatement.executeUpdate();
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e); //only at development not at production/live
					throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
				}
				
				return c;
	}


	@Override
	public Customer getCustomer(int user_id) throws BusinessException {
		Customer customer = null;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select firstname,lastname, email, phonenumber, city,  age, gender, customerstatus, user_id, customer_id from banking_schema.customers where user_id =? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer = new Customer();
				customer.setAge(resultSet.getInt("age"));
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhoneNumber(resultSet.getString("phonenumber"));
				customer.setCity(resultSet.getString("city"));
				customer.setCustomerStatus(resultSet.getString("firstname"));
				customer.setGender(resultSet.getString("gender"));
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setCustomer_id(resultSet.getInt("customer_id"));


			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
		Log.info(customer);
			
			
		return customer;
	}


	@Override
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException {
		List<Account> accountList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select customer_id,account_id, balance, accountstatus from banking_schema.accounts where customer_id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer_id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Account account=new Account();
				account.setAccountid(resultSet.getInt("account_id"));
				account.setCustomerid(resultSet.getInt("customer_id"));
				account.setAccountstatus(resultSet.getInt("accountstatus"));
				account.setBalance(resultSet.getInt("balance"));
				
				accountList.add(account);
			}
			if(accountList.size()==0) {
				throw new BusinessException("No accounts found for the current customer ");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error");
		}
		return accountList;
	}


	@Override
	public int createNewAccount(int customer_id, int deposit) throws BusinessException {
		// TODO Auto-generated method stub
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_schema.accounts(customer_id, balance)"
					+ " values(?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer_id);
			preparedStatement.setInt(2, deposit);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Log.info(e); //only at development not at production/live
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
//		Log.info(c);
		return c;
	}


	@Override
	public int setNewAccountBalance(int account_id, double newBalance) throws BusinessException {
		// TODO Auto-generated method stub
				int c=0;
				try(Connection connection=PostgresConnection.getConnection()){
					String sql = "update banking_schema.accounts set balance =? where account_id=?";
					PreparedStatement preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setDouble(1, newBalance);
					preparedStatement.setInt(2, account_id);
					c=preparedStatement.executeUpdate();
				} catch (ClassNotFoundException | SQLException e) {
					Log.info(e); //only at development not at production/live
					throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
				}
				Log.info(c);
				return c;
	}


	@Override
	public Employee employeelogIn(String username, String password) throws BusinessException {
		Employee employee = null;
		
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select username, password, firstname, lastname, employee_id from banking_schema.employees where username=? and password =? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				employee = new Employee();
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setFirstName(resultSet.getString("firstname"));
				employee.setLastName(resultSet.getString("lastname"));
				employee.setEmployee_id(resultSet.getString("employee_id"));

			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
		Log.info(employee);
			
			
		return employee;
	}


	@Override
	public int registerNewEmployee(String username, String password, String firstname, String lastname) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_schema.employees(username, password, firstname, lastname) values(?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstname);
			preparedStatement.setString(4, lastname);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //only at development not at production/live
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
		
		return c;
	}


	@Override
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException {
		List<Account> accountList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select customer_id,account_id, balance, accountstatus from banking_schema.accounts";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				if(resultSet.getInt("accountstatus") == status) {
				Account account=new Account();
				account.setAccountid(resultSet.getInt("account_id"));
				account.setCustomerid(resultSet.getInt("customer_id"));
				account.setAccountstatus(resultSet.getInt("accountstatus"));
				account.setBalance(resultSet.getInt("balance"));
				
				accountList.add(account);
				}
			}
//			if(accountList.size()==0) {
//				throw new BusinessException("No accounts found for the current customer ");
//			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error");
		}
		return accountList;
	}


	@Override
	public int approveAccount(int accountid, int status) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "update banking_schema.accounts set accountstatus =? where account_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, status);
			preparedStatement.setInt(2, accountid);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Log.info(e); //only at development not at production/live
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
		Log.info(c);
		return c;
	}


	@Override
	public int rejectAccount(int accountid) throws BusinessException {
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "delete from banking_schema.accounts where account_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountid);
			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Log.info(e); //only at development not at production/live
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
		Log.info(c);
		return c;
	}


	@Override
	public Customer getCustomerByCustomerId(int customer_id) throws BusinessException {
		Customer customer = null;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select firstname,lastname, email, phonenumber, city,  age, gender, customerstatus, user_id, customer_id from banking_schema.customers where customer_id =? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer_id);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer = new Customer();
				customer.setAge(resultSet.getInt("age"));
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhoneNumber(resultSet.getString("phonenumber"));
				customer.setCity(resultSet.getString("city"));
				customer.setCustomerStatus(resultSet.getString("firstname"));
				customer.setGender(resultSet.getString("gender"));
				customer.setUser_id(resultSet.getInt("user_id"));
				customer.setCustomer_id(resultSet.getInt("customer_id"));


			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
		Log.info(customer);
			
			
		return customer;
	}


	@Override
	public Account getAccountByAccountId(int account_id) throws BusinessException {
		Account account = null;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select customer_id, account_id, balance, accountstatus from banking_schema.accounts where account_id =? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, account_id);

			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				account = new Account();
				account.setCustomerid(resultSet.getInt("customer_id"));
				account.setAccountid(resultSet.getInt("account_id"));
				account.setBalance(resultSet.getInt("balance"));
				account.setAccountstatus(resultSet.getInt("accountstatus"));


			}
			} catch (ClassNotFoundException | SQLException e) {
//				Log.info(user);
				Log.info(e);
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}
//		Log.info(customer);
			
			
		return account;
	}


	@Override
	public void transferMoney(int accountid, int accountid2, double newAccountBalance, double newAccountBalance2) throws BusinessException {
		Connection connection = null;
		int c = 0;
		int c1 = 0;
		try {
			connection=PostgresConnection.getConnection();
			String sql1 = "update banking_schema.accounts set balance =? where account_id=?";
			String sql2 = "update banking_schema.accounts set balance =? where account_id=?";
			connection.setAutoCommit(false);
			
			PreparedStatement preparedStatement=connection.prepareStatement(sql1);
			preparedStatement.setDouble(1, newAccountBalance);
			preparedStatement.setInt(2, accountid);
			c = preparedStatement.executeUpdate();
			
			preparedStatement=connection.prepareStatement(sql2);
			preparedStatement.setDouble(1, newAccountBalance2);
			preparedStatement.setInt(2, accountid2);
			c1 = c1 = preparedStatement.executeUpdate();
			
			connection.commit();
			if ((c + c1) == 2) {
				Log.info("The transaction was successful");
			} 
		} catch (ClassNotFoundException | SQLException e) {
			Log.info(e); //only at development not at production/live
			try {
				connection.rollback();
				Log.warn("Rolling back the query");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				throw new BusinessException("Internal error occured... with roll back");

			}
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
		
	}


	@Override
	public int recordTransaction(Transaction transaction) throws BusinessException {
		java.util.Date date = new Date();
		
		int c=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "insert into banking_schema.transactions (date, customerid, target_id, amount, status, type, accountid) values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, new java.sql.Timestamp(date.getTime()));
			preparedStatement.setInt(2, transaction.getCustomerid());
			preparedStatement.setInt(3, transaction.getTarget_id());
			preparedStatement.setDouble(4, transaction.getAmount());
			preparedStatement.setString(5, transaction.getStatus());
			preparedStatement.setInt(6, transaction.getType());
			preparedStatement.setInt(7, transaction.getAccountid());


			c=preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); //only at development not at production/live
			throw new BusinessException("Internal error occured... Please contact SYSSADMIN");
		}
		
		return c;
		
	}


	@Override
	public List<Transaction> getLogOfTransactions() throws BusinessException {
		List<Transaction> transactions = new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select date, customerid, accountid, target_id, amount, status, type from banking_schema.transactions";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Transaction transaction=new Transaction();
				transaction.setAccountid(resultSet.getInt("accountid"));
				transaction.setCustomerid(resultSet.getInt("customerid"));
				transaction.setDate(resultSet.getTimestamp("date"));
				transaction.setTarget_id(resultSet.getInt("target_id"));
				transaction.setAmount(resultSet.getDouble("amount"));
				transaction.setStatus(resultSet.getString("status"));
				transaction.setType(resultSet.getInt("type"));

				transactions.add(transaction);
			}
//			if(accountList.size()==0) {
//				throw new BusinessException("No accounts found for the current customer ");
//			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error");
		}
		return transactions;
	}

	}

