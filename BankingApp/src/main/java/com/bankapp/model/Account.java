package com.bankapp.model;

public class Account {

	private int customerid;
	private int accountid;
	private double balance;
	private int accountstatus;
	
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccountstatus() {
		return accountstatus;
	}
	public void setAccountstatus(int accountstatus) {
		this.accountstatus = accountstatus;
	}
	public Account(int customerid, int accountid, int balance, int accountstatus) {
		super();
		this.customerid = customerid;
		this.accountid = accountid;
		this.balance = balance;
		this.accountstatus = accountstatus;
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Account [accountid=" + accountid + ", balance=" + balance + "]";
	}
	
	
}
