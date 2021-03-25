package com.bankapp.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankapp.bank.dao.BankCRUDDAO;
import com.bankapp.dao.dbutil.PostgresConnection;
import com.bankapp.exception.BusinessException;
import com.bankapp.model.Transaction;

public class BankCRUDDAOImpl implements BankCRUDDAO {
	private static Logger Log = Logger.getLogger(BankCRUDDAOImpl.class);


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
	public void transferMoney(int accountid, int accountid2, double newAccountBalance, double newAccountBalance2)
			throws BusinessException {
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
			throw new BusinessException("Internal error");
		}
		return transactions;
	}

}
