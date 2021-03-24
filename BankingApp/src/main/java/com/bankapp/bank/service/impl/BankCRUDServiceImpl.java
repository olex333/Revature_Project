package com.bankapp.bank.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.bankapp.bank.dao.BankCRUDDAO;
import com.bankapp.bank.dao.impl.BankCRUDDAOImpl;
import com.bankapp.bank.service.BankCRUDService;
import com.bankapp.bin.CustomerLoginServiceImpl;
import com.bankapp.exception.BusinessException;
import com.bankapp.main.menu.PrintMenu;
import com.bankapp.model.Account;
import com.bankapp.model.Transaction;

public class BankCRUDServiceImpl implements BankCRUDService {
	private static Logger Log = Logger.getLogger(CustomerLoginServiceImpl.class);
	PrintMenu menu = new PrintMenu();
	BankCRUDDAO bankCRUD = new BankCRUDDAOImpl();


	@Override
	public int createNewAccount(int customer_id, int deposit) throws BusinessException {
		int c = 0;
		try {
			c = bankCRUD.createNewAccount(customer_id, deposit);
		} catch (BusinessException e) {

		}
		return c;
	}

	@Override
	public int depositIntoAccount(int account_id, double newBalance) throws BusinessException {
		int c = 0;
		if (newBalance < 0) {
			throw new BusinessException("The withdraw amount exceeds the balance");
		}
		try {
			c = bankCRUD.setNewAccountBalance(account_id, newBalance);
		} catch (BusinessException e) {
			Log.info(e);
		}
		return c;
	}

	@Override
	public Account withdrawFromAccount(Account account, int withdraw, Transaction transaction) throws BusinessException {
	int c = 0;
	double balance = account.getBalance();
	double newBalance = balance - withdraw;
	if (newBalance < 0) {
		transaction.setStatus("Failure");
		c = bankCRUD.recordTransaction(transaction);
		throw new BusinessException("The withdraw amount exceeds the balance");
	} else {
	try {
		c = bankCRUD.setNewAccountBalance(account.getAccountid(), newBalance);
		account.setBalance(newBalance);
		Log.info("The withdraw was succesful");
		transaction.setStatus("Success");
		c = bankCRUD.recordTransaction(transaction);
	} catch (BusinessException e) {
		Log.info(e);
	}
	}
	return account;
	}

	@Override
	public Account transferMoney(Account account, Account receivingAccount, double transaction_amount) throws BusinessException {
	double newAccountBalance = account.getBalance() - transaction_amount;
	double newReceivingAccountBalance = receivingAccount.getBalance() + transaction_amount ;
	if (newAccountBalance < 0 || newReceivingAccountBalance < 0 ) {
		throw new BusinessException("Transaction amount exceeds the account balance");
	}
	Log.info("The new balance is " + newAccountBalance);
	bankCRUD.transferMoney(account.getAccountid(), receivingAccount.getAccountid(), newAccountBalance, newReceivingAccountBalance );
	account.setBalance(newAccountBalance);
	return account;
	}

	@Override
	public List<Transaction> getLogOfTransactions() throws BusinessException {
		List<Transaction> transactions = bankCRUD.getLogOfTransactions();
		if (transactions.size() == 0) {
			throw new BusinessException("No transactions found ");
		}
		return transactions;
	}

}
