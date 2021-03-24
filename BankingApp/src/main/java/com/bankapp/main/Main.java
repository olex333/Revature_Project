package com.bankapp.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bankapp.exception.BusinessException;
import com.bankapp.main.menu.MenuLogic;
import com.bankapp.main.menu.PrintMenu;
import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.model.Employee;
import com.bankapp.model.Transaction;
import com.bankapp.model.User;
import com.bankapp.user.customer.service.CustomerLoginService;
import com.bankapp.user.customer.service.impl.CustomerLoginServiceImpl;

public class Main {
//	private static CustomerLoginService customerLoginService = new CustomerLoginServiceImpl();
	private static Logger Log = Logger.getLogger(Main.class);
	PrintMenu menu = new PrintMenu();
	private static MenuLogic menuLogic = new MenuLogic();

	public static void main(String[] args) {
		Log.info("Welcome to the Alexei bank" + " app V1.0");
		Log.info("================");
		PrintMenu menu = new PrintMenu();

		int ch = 0;
		Scanner scanner = new Scanner(System.in);

		do {
//			Log.info("Are you a employee or customer?");//
			menu.printUserMenu();
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}

			switch (ch) {
			// 1
//			*******************************************************************************
			case 1:

				menu.printCustomerLoginMenu();
				int ch_1 = 0;
				do {

					try {
						ch_1 = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {

					}
					boolean logedIn = false;
					switch (ch_1) {
					//// 1111 User logging
//					*******************************************************************************
					case 1:
						// Login
						User user;
						Customer customer = null;
						;
						try {
							user = menuLogic.customerLogIn(scanner);
							customer = menuLogic.getCustomerByUsername(user.getUsername());
							logedIn = true;
							Log.info(customer);
						} catch (BusinessException e) {
							// TODO Auto-generated catch block
//						e.printStackTrace();
							Log.info(e);
//							menu.printCustomerLoginMenu();
						}
						if (logedIn) {
							int ch11 = 0;
							do {
								menu.printLogedInMenu();
								try {
									ch11 = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									Log.warn(e);
								}
//								menu.printLogedInMenu();
								switch (ch11) {
								// View Accounts
//								*******************************************************************************
								case 1:
									Log.info("Here are the accounts");
									List<Account> accounts = menuLogic.getAllAcountsById(customer.getCustomer_id());
									for (int i = 0; i < accounts.size(); i++) {
										Log.info((i + 1) + ") account id " + accounts.get(i).getAccountid()
												+ " with balance " + accounts.get(i).getBalance());
									}
									int ch_a = 0;                            

									do {
										boolean activeAccount = false;
										Account account = null;
										Log.info("Select an account or press 9 to exit");
										try {
											ch_a = Integer.parseInt(scanner.nextLine());
											if (ch_a == 9) {
												break;
											}
											account = accounts.get((ch_a - 1));
											activeAccount = true;
											Log.info(activeAccount);

										} catch (NumberFormatException | IndexOutOfBoundsException e) {
											Log.warn(e);
											Log.warn("Wrong choice or wrong format");
										}
										Log.info(activeAccount);
										if (activeAccount) {
											int ch22 = 0;
											do {
												menu.printAccountOptionsMenu();
												try {
													ch22 = Integer.parseInt(scanner.nextLine());
												} catch (NumberFormatException e) {
													Log.warn(e);
												}

												switch (ch22) {

//											*******************************************************************************
												case 1:
													Log.info("The account balance is : " + account.getBalance());
													break;

//											*******************************************************************************
												case 2:
													try {
														account = menuLogic.depositIntoAccount(scanner, account, customer);
													} catch (BusinessException e) {
														Log.info(e);
													}
													break;

//													*******************************************************************************
												case 3:
													try {
														account = menuLogic.withdrawFromAccount(scanner, account, customer);
													} catch (BusinessException e) {
														Log.info(e);
													}
													break;

//													*******************************************************************************
												case 4:
													// Here we do the transaction;
													Log.info("Under Construction");
													try {
														account = menuLogic.transferMoney(scanner, account, customer);
														Log.info("The transfer was succesful");
													} catch (BusinessException e) {
														Log.info("Transaction failure");
													}
													break;

//													*******************************************************************************
												case 5:
													Log.info("Going to previous Menu");
													break;
												}
											} while (ch22 != 5);
										}
									} while (ch_a != 9);
									break;

								// Create Account
//									*******************************************************************************
								case 2:
									try {
										menuLogic.createNewAccount(customer.getCustomer_id(), scanner);
									} catch (BusinessException e) {
										Log.info(e);
									}
									break;

								// Exit
//									*******************************************************************************
								case 3:
									Log.info("Going back to Previos menu");

									break;
//									*******************************************************************************
								default:
									Log.info(" Invalid Choice... Please enter a proper choice between 1-3 only.......");
									break;
								}
							} while (ch11 != 3);
						}

						menu.printCustomerLoginMenu();
						break;

//						*******************************************************************************
					case 2:
						// User Registration
						Log.info("User registration");
						try {
//							menuLogic.userRegistration(scanner);
							menuLogic.Registration(scanner);
						} catch (BusinessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;

//						*******************************************************************************
					case 3:
						// Exit
						Log.info("Going to the previous Menu");
						break;

//						*******************************************************************************
					default:
						Log.info("1.2 Invalid Choice... Please enter a proper choice between 1-3 only.......");
						break;
					}
				} while (ch_1 != 3);

				break;
//			*******************************************************************************
			case 2:
//				Log.info("Under Construction");
//				menu.printEmployeeLoginMenu();
				int ch22 = 0;
				do {
					menu.printEmployeeLoginMenu();
					try {
						ch22 = Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {

					}
					boolean employeelogedIn = false;
					switch (ch22) {

//					**************************************************************************************

					case 1:
						Employee employee;
						try {
							employee = menuLogic.EmpoyeeLogIn(scanner);
							employeelogedIn = true;
							Log.info(employee);
							Log.info(employeelogedIn);

						} catch (BusinessException e) {
//					e.printStackTrace();
							Log.info(e);
						}

						if (employeelogedIn) {
							int ch33 = 0;
							do {
								menu.printEmployeeOptionsMenu();
								try {
									ch33 = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									Log.warn(e);
								}

								switch (ch33) {
								// case 1 would be approve or reject accounts
								case 1:
									do {
									Log.info("Here are the accounts pending approval or rejection");
									List<Account> accounts = menuLogic.getAllPendingAcounts();
									Log.info("Select an account or press 9 to exit");
									for (int i = 0; i < accounts.size(); i++) {
										Log.info((i + 1) + ") account id " + accounts.get(i).getAccountid()
												+ " with balance " + accounts.get(i).getBalance());
									}
//									do {
										boolean activeEmployee = false;
										Account account = null;
										int ch5 = 0;
//										Log.info("Select an account or press 9 to exit");
										try {
											ch5 = Integer.parseInt(scanner.nextLine());
											if (ch5 == 9) {
//												menu.printEmployeeOptionsMenu();
												break;
											}
											account = accounts.get((ch5 - 1));
											activeEmployee = true;
											Log.info(activeEmployee);

										} catch (NumberFormatException | IndexOutOfBoundsException e) {
											Log.warn(e);
											Log.warn("Wrong choice or wrong format");
										}
										Log.info(activeEmployee);
										if (activeEmployee) {
											int ch6 = 0;
											do {
												menu.printEmployeePendingAccountAction();
												try {
													ch6 = Integer.parseInt(scanner.nextLine());
												} catch (NumberFormatException e) {
													Log.warn(e);
												}

												switch (ch6) {
												case 1:
													try {
														menuLogic.approveAccount(account.getAccountid());
														Log.info("Approved account succesfully");
													} catch (BusinessException e) {
														Log.warn("Unable to Approve the account");
													}
													ch6 = 3;
													break;
												case 2:
													try {
														menuLogic.rejectAccount(account.getAccountid());
														Log.info("Rejected account succesfully");
													} catch (BusinessException e) {
														Log.warn("Unable to Reject the account");
													}
//													Log.info("Under Construction");
													ch6 = 3;
													break;
												case 3:
													Log.info("Exiting the menu");
													break;

												default:
													Log.info(
															" Invalid Choice... Please enter a proper choice between 1-3 only.......");
													break;
												}
											} while (ch6 != 3);
										}
									} while (ch != 9);

									Log.info("Under Construction");

									break;

								// view customers bank account
								case 2:
									Log.info("Under Construction");
									Customer customer = null;
									try {
					
										customer = menuLogic.getCustomerById(scanner);
										List<Account> accounts = menuLogic.getAllAcountsById(customer.getCustomer_id());
										for (int i = 0; i < accounts.size(); i++) {
											Log.info((i + 1) + ") account id " + accounts.get(i).getAccountid()
													+ " with balance " + accounts.get(i).getBalance());
										}
										
										Log.info(customer);
									} catch (BusinessException e) {

										Log.info(e);
//										menu.printCustomerLoginMenu()
									}
									break;

								case 3:
									Log.info("Here is the Log of Transactions");
//									****
//									****
//									********
//									****
									try {
										
									List<Transaction> transactions = menuLogic.LogOfTransactions();
//									Transaction transaction = null;
									for (int i = 0; i < transactions.size(); i++) {
//										transaction = transactions.get(i);
										Log.info(transactions.get(i).toString());
												
									}
									} catch (BusinessException e) {
										Log.error("Unable to retrieve the transaction list");
									}
									break;

								case 4:
									Log.info("Exiting the current Menu and going to the previous Menu");

									break;
									
								default:
									Log.info(
											" Invalid Choice... Please enter a proper choice between 1-4 only.......");
									break;
								}
							} while (ch33 != 4);
						}
						break;

//					*******************************************************************************

					case 2:
						Log.info("Registration of new employee");
						try {
							menuLogic.createNewEmployeeAccount(scanner);
						} catch (BusinessException e) {
							Log.info(e);
						}
						break;
						
					case 3:
						Log.info("Exiting to previous Menu");
						break;
						
					default:
						Log.info(" Invalid Choice... Please enter a proper choice between 1-3 only.......");
						
						break;
							

					}
				} while (ch22 != 3);
				//// 1111 User logging
				break;

//				*******************************************************************************
			case 3:
				Log.info("Thank you for using our banking app," + " Hope you to see you again in the future");
				break;

			default:
				Log.info("Invalid Choice... Please enter a proper choice between 1-3 only.......");
			}

		} while (ch != 3);

	}

}
