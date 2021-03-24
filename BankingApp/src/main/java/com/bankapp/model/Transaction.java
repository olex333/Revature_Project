package com.bankapp.model;

import java.sql.Timestamp;
import java.util.Date;

public class Transaction {
	private Timestamp date;
	private int customerid;
	private int accountid;
	private int target_id;
	private double amount;
	private String status;
	private int type;
	
	public Transaction(Timestamp date, int customerid, int accountid, int target_id, double amount, String status) {
		super();
		this.date = date;
		this.customerid = customerid;
		this.accountid = accountid;
		this.target_id = target_id;
		this.amount = amount;
		this.status = status;
	}
	
	
	
	public Transaction(Timestamp date, int customerid, int accountid, int target_id, double amount, String status,
			int type) {
		super();
		this.date = date;
		this.customerid = customerid;
		this.accountid = accountid;
		this.target_id = target_id;
		this.amount = amount;
		this.status = status;
		this.type = type;
	}



	public Transaction() {
		// TODO Auto-generated constructor stub
	}






	@Override
	public String toString() {
		return "Transaction [date=" + date + ", customerid=" + customerid + ", accountid=" + accountid + ", target_id="
				+ target_id + ", amount=" + amount + ", status=" + status + ", type=" + type + "]";
	}



	public Date getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
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
	public int getTarget_id() {
		return target_id;
	}
	public void setTarget_id(int target_id) {
		this.target_id = target_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

	

}
