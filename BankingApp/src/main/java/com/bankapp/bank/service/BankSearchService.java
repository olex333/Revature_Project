package com.bankapp.bank.service;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;
import com.bankapp.model.Transaction;

public interface BankSearchService {

	public List<Account> getAllAccountsById(int customer_id) throws BusinessException;
	public Account getAccountByAccountId(int account_id) throws BusinessException;
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException;
}
