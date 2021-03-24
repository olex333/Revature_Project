package com.bankapp.main.menu;

import org.apache.log4j.Logger;



public class PrintMenu {
	private static Logger Log = Logger.getLogger(PrintMenu.class);

	
	public void printUserMenu() {
		Log.info("User MENU");
		Log.info("------------------");
		Log.info("Are you a employee or customer?");
		Log.info("1) Customer");
		Log.info("2) Employee");
		Log.info("3) Exit");
	}
	
	public void printCustomerLoginMenu() {
		Log.info("\nMenu");
		Log.info("1) Login");
		Log.info("2) Register for an account");
		Log.info("3) Exit");
	}
	
	public void printEmployeeLoginMenu() {
		Log.info("\n Employee Menu");
		Log.info("1) Login");
		Log.info("2) Register for an account");
		Log.info("3) Exit");
	}
	
	public void printLogedInMenu() {
		Log.info("\nMenu");
		Log.info("1) View accounts");
		Log.info("2) Create account");
		Log.info("3) Exit");
	}
	
	public void printAccountOptionsMenu() {
		Log.info("\nMenu");
		Log.info("1) View balance");
		Log.info("2) Make a deposit");
		Log.info("3) Withdraw money");
		Log.info("4) transfer money to another account");
		Log.info("5) Exit");
	}
	
	public void printEmployeeOptionsMenu() {
		Log.info("\nMenu");
		Log.info("1) Aprove or Reject Accounts");
		Log.info("2) View customers Bank Account");
		Log.info("3) View log of all the transactions");
		Log.info("4) Exit");
	}
	
	public void printEmployeePendingAccountAction() {
		Log.info("\nMenu");
		Log.info("1) Aprove Account");
		Log.info("2) Reject Account");
		Log.info("3) Exit");
	}
	
	
	
//	public void 

}
