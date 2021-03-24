package com.bankapp.bank.dao;

import java.util.List;

import com.bankapp.exception.BusinessException;
import com.bankapp.model.Account;

public interface BankSearchDAO {
	public List<Account> getAllAccountsById(int customer_id) throws BusinessException;
	public List<Account> getAllAccountsByStatus(int status) throws BusinessException;
	public Account getAccountByAccountId(int account_id) throws BusinessException;
}
