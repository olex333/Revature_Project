package com.bankapp.bank.service;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Transaction;

public interface BankCRUDService {
	
	public int createNewAccount(int customer_id, int deposit) throws BusinessException; 
	public int depositIntoAccount(int accountid, double newBalance) throws BusinessException;
	public Account withdrawFromAccount(Account account, int withdraw, Transaction transaction) throws BusinessException;
	public Account transferMoney(Account account, Account receivingAccount, double transaction_amount) throws BusinessException; 
	public List<Transaction> getLogOfTransactions() throws BusinessException;
}
