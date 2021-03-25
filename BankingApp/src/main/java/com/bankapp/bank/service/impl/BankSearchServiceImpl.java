package com.bankapp.bank.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.bankapp.bank.dao.BankSearchDAO;
import com.bankapp.bank.dao.impl.BankSearchDAOImpl;
import com.bankapp.bank.service.BankSearchService;
import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Transaction;

public class BankSearchServiceImpl implements BankSearchService {
	private static Logger Log = Logger.getLogger(BankSearchServiceImpl.class);

	BankSearchDAO BankSearch = new BankSearchDAOImpl();
	
	@Override
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException {

		List<Account> accounts = BankSearch.getAllAccountsById(customer_id);
		if (accounts.size() == 0) {
			throw new BusinessException("No Accounts found for current customer ");
		}
		return accounts;
	}
	

	@Override
	public Account getAccountByAccountId(int account_id) throws BusinessException {
		Account account = null;
		account = BankSearch.getAccountByAccountId(account_id);
		if (account == null) {
			throw new BusinessException();
		}
		
		return account;
	}

	@Override
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException {
		List<Account> accounts = BankSearch.getAllAccountsByStatus(status);
		if (accounts.size() == 0) {
			throw new BusinessException("No Accounts found for current customer ");
		}
		return accounts;
	}

}
