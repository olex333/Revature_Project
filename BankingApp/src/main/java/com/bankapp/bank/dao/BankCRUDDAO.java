package com.bankapp.bank.dao;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Transaction;

public interface BankCRUDDAO {

	public int createNewAccount(int customer_id, int deposit) throws BusinessException;
	public int setNewAccountBalance(int account_id, double newBalance) throws BusinessException;
	public int approveAccount(int accountid, int status) throws BusinessException; 		
	public int rejectAccount(int accountid) throws BusinessException; 
	public void transferMoney(int accountid, int accountid2, double newAccountBalance, double newAccountBalance2) throws BusinessException;     // Bank service CRUD 
	public int recordTransaction(Transaction transaction ) throws BusinessException;   
	public List<Transaction> getLogOfTransactions() throws BusinessException;			
}
