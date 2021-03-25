package com.bankapp.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bankapp.bank.dao.BankSearchDAO;
import com.bankapp.dao.dbutil.PostgresConnection;
import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;

public class BankSearchDAOImpl implements BankSearchDAO {
	private static Logger Log = Logger.getLogger(BankSearchDAOImpl.class);


	@Override
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException {
		List<Account> accountList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql = "select customer_id,account_id, balance, accountstatus from banking_schema.accounts where customer_id = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer_id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				if (resultSet.getInt("accountstatus") == 1) {
				Account account=new Account();
				account.setAccountid(resultSet.getInt("account_id"));
				account.setCustomerid(resultSet.getInt("customer_id"));
				account.setAccountstatus(resultSet.getInt("accountstatus"));
				account.setBalance(resultSet.getInt("balance"));
				
				accountList.add(account);
				}
			}
			if(accountList.size()==0) {
				throw new BusinessException("No accounts found for the current customer ");
			}
		} catch (ClassNotFoundException | SQLException e) {
			Log.warn("Internal error");
			throw new BusinessException("Internal error");
		}
		return accountList;
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

		} catch (ClassNotFoundException | SQLException e) {
			Log.warn("Internal error");
			throw new BusinessException("Internal error");
		}
		return accountList;
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
				Log.warn("Internal Error");
				throw new BusinessException("Internal Error");
			}

			
			
		return account;
	}

}
