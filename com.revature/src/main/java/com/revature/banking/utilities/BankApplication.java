package com.revature.banking.utilities;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import com.revature.banking.dao.accountDAOI;
import com.revature.banking.dao.loginDAOI;
import com.revature.banking.dao.userDAOI;
import com.revature.banking.models.accounts;
import com.revature.banking.models.login;
import com.revature.banking.models.users;

public class BankApplication {
	
	public static void customerMenu(login L) {
		
		int input = -1;
		double funds = 0;
		String s;
		DecimalFormat df = new DecimalFormat("#.00"); 
		Scanner scan = new Scanner(System.in);
		accountDAOI ADAO = new accountDAOI();
		List<accounts> acc = ADAO.selectAllAccounts();
		accounts checking = new accounts();
		accounts savings = new accounts();
		
		for(int i = 0; i < acc.size(); i++) {
			if(acc.get(i).getLogin_id() == L.getId() && acc.get(i).getType().equals("checking")) {
				checking = acc.get(i);
			}
			if(acc.get(i).getLogin_id() == L.getId() && acc.get(i).getType().equals("savings")) {
				savings = acc.get(i);
			}
		}
	
		while(input != 0) {
			System.out.println(":: Customer Menu ::");
			System.out.println("Press 1 to view balance, press 2 to deposit,  press 3 to withdraw, press 4 to transfer, press 5 to apply for new account, press 0 to exit.");
			input = scan.nextInt();
			
			// View account balance
			if(input == 1) {
				System.out.println("Press 1 to view checking, press 2 to view savings, press 0 to exit.");
				input = scan.nextInt();
				if(input == 1) {
					if(checking != null && checking.getStatus().equals("active")) {
						s = df.format(checking.getFunds());
						System.out.println("Available funds: $" + s);
					} else {
						System.out.println("No checking account found!");
					}
				} else if(input == 2) {
					if(savings != null && savings.getStatus().equals("active")) {
						s = df.format(savings.getFunds());
						System.out.println("Available funds: $" + s);
					} else {
						System.out.println("No savings account found!");
					}
				} else if(input != 0) {
					System.out.println("Invalid input!");
				}
			
			// Deposit into account
			} else if(input == 2) {
				System.out.println("Press 1 to deposit into checking, press 2 to deposit into savings, press 0 to exit.");
				input = scan.nextInt();
				if(input == 1) {
					if(checking != null && checking.getStatus().equals("active")) {
						System.out.println("How much are you depositing into checking?");
						funds = scan.nextDouble();
						if(funds > 0) {
							checking.setFunds(checking.getFunds() + funds);
							ADAO.updateAccount(checking);
							System.out.println("$" + funds + " deposited to checking!");
						} else {
							System.out.println("Invalid amount!");
						}
					} else {
						System.out.println("No checking account found!");
					}
				} else if(input == 2) {
					if(savings != null && savings.getStatus().equals("active")) {
						System.out.println("How much are you depositing into savings?");
						funds = scan.nextDouble();
						if(funds > 0) {
							savings.setFunds(savings.getFunds() + funds);
							ADAO.updateAccount(savings);
							System.out.println("$" + funds + " deposited to savings!");
						} else {
							System.out.println("Invalid amount!");
						}
					} else {
						System.out.println("No savings account found!");
					}
				} else if(input != 0){
					System.out.println("Invalid input!");
				}
			
			// Withdraw from account
			} else if(input == 3) {
				System.out.println("Press 1 to withdraw from checking, press 2 to withdraw from savings, press 0 to exit.");
				input = scan.nextInt();
				if(input == 1) {
					if(checking != null && checking.getStatus().equals("active")) {
						System.out.println("How much are you withdrawing from checking?");
						funds = scan.nextDouble();
						if(funds > 0) {
							if(checking.getFunds() - funds >= 0) {
								checking.setFunds(checking.getFunds() - funds);
								ADAO.updateAccount(checking);
								System.out.println("$" + funds + " withdrawn from checking!");
							} else {
								System.out.println("Insufficient funds!");
							}
						} else {
							System.out.println("Invalid amount!");
						}
					} else {
						System.out.println("No checking account found!");
					}
				} else if(input == 2) {
					if(savings != null && savings.getStatus().equals("active")) {
						System.out.println("How much are you withdrawing from savings?");
						funds = scan.nextDouble();
						if(funds > 0) {
							if(savings.getFunds() - funds >= 0) {
								savings.setFunds(savings.getFunds() - funds);
								ADAO.updateAccount(savings);
								System.out.println("$" + funds + " withdrawn from savings!");
							} else {
								System.out.println("Insufficient funds!");
							}
						} else {
							System.out.println("Invalid amount!");
						}
					} else {
						System.out.println("No savings account found!");
					}
				} else if(input != 0) {
					System.out.println("Invalid input!");
				}
				
			// Transfer funds
			} else if(input == 4) {
				System.out.println("Press 1 to transfer from checking to savings, press 2 to transfer from savings to checking.");
				System.out.println("Press 3 to transfer from checking to outside account, press 4 to transfer from savings to outside account.");
				System.out.println("Press 0 to exit.");
				input = scan.nextInt();
				if(input == 1) {
					if(checking != null && checking.getStatus().equals("active") && savings != null && savings.getStatus().equals("active")) {
						System.out.println("How much are you transferring?");
						funds = scan.nextDouble();
						if(funds > 0) {
							if(checking.getFunds() - funds >= 0) {
								checking.setFunds(checking.getFunds() - funds);
								savings.setFunds(savings.getFunds() + funds);
								ADAO.updateAccount(checking);
								ADAO.updateAccount(savings);
								System.out.println("$" + funds + " transferred from checking to savings!");
							} else {
								System.out.println("Insufficient funds!");
							}
						} else {
							System.out.println("Invalid amount!");
						}
					} else {
						System.out.println("No checking or savings account found!");
					}
				} else if(input == 2) {
					if(checking != null && checking.getStatus().equals("active") && savings != null && savings.getStatus().equals("active")) {
						System.out.println("How much are you transferring?");
						funds = scan.nextDouble();
						if(funds > 0) {
							if(savings.getFunds() - funds >= 0) {
								checking.setFunds(checking.getFunds() + funds);
								savings.setFunds(savings.getFunds() - funds);
								ADAO.updateAccount(checking);
								ADAO.updateAccount(savings);
								System.out.println("$" + funds + " transferred from savings to checking!");
							} else {
								System.out.println("Insufficient funds!");
							}
						} else {
							System.out.println("Invalid amount!");
						}
					} else {
						System.out.println("No checking or savings account found!");
					}
				} else if(input == 3) {
					if(checking != null && checking.getStatus().equals("active")) {
						System.out.println("Enter account number that you're transferring to:");
						input = scan.nextInt();
						accounts a = new accounts();
						a = ADAO.selectAccountById(input);
						if(a != null) {
							System.out.println("How much are you transferring?");
							funds = scan.nextDouble();
							if(funds > 0) {
								if(checking.getFunds() - funds >= 0) {
									checking.setFunds(checking.getFunds() - funds);
									a.setFunds(a.getFunds() + funds);
									ADAO.updateAccount(checking);
									ADAO.updateAccount(a);
									System.out.println("$" + funds + " transferred from checking to outside account!");
								} else {
									System.out.println("Insufficient funds!");
								}
							} else {
								System.out.println("Invalid amount!");
							}
						} else {
							System.out.println("Invalid account number!");
						}
					} else {
						System.out.println("No checking account found!");
					}
				} else if(input == 4) {
					if(savings != null && savings.getStatus().equals("active")) {
						System.out.println("Enter account number that you're transferring to:");
						input = scan.nextInt();
						accounts a = new accounts();
						a = ADAO.selectAccountById(input);
						if(a != null) {
							System.out.println("How much are you transferring?");
							funds = scan.nextDouble();
							if(funds > 0) {
								if(savings.getFunds() - funds >= 0) {
									savings.setFunds(savings.getFunds() - funds);
									a.setFunds(a.getFunds() + funds);
									ADAO.updateAccount(savings);
									ADAO.updateAccount(a);
									System.out.println("$" + funds + " transferred from savings to outside account!");
								} else {
									System.out.println("Insufficient funds!");
								}
							} else {
								System.out.println("Invalid amount!");
							}
						} else {
							System.out.println("Invalid account number!");
						}
					} else {
						System.out.println("No savings account found!");
					}
				} else if(input != 0) {
					System.out.println("Invalid input!");
				}
			
			// Apply for account
			} else if(input == 5) {
				System.out.println("Press 1 to apply for checking, press 2 to apply for savings, press 0 to exit.");
				input = scan.nextInt();
				if(input == 1) {
					System.out.println("How much is the starting balance?");
					funds = scan.nextDouble();
					if(funds >= 0) {
						accounts a = new accounts();
						List<accounts> A = ADAO.selectAllAccounts();
						a.setNum(A.size() + 1);
						a.setFunds(funds);
						a.setType("checking");
						a.setStatus("pending");
						a.setLogin_id(L.getId());
						ADAO.insertIntoAccounts(a);
						System.out.println("Checking account created!");
					} else {
						System.out.println("Invalid amount!");
					}
				} else if(input == 2) {
					System.out.println("How much is the starting balance?");
					funds = scan.nextDouble();
					if(funds >= 0) {
						accounts a = new accounts();
						List<accounts> A = ADAO.selectAllAccounts();
						a.setNum(A.size() + 1);
						a.setFunds(funds);
						a.setType("savings");
						a.setStatus("pending");
						a.setLogin_id(L.getId());
						ADAO.insertIntoAccounts(a);
						System.out.println("Savings account created!");
					} else {
						System.out.println("Invalid amount!");
					}
				} else if(input != 0) {
					System.out.println("Invalid input!");
				}
			}
		}
	}
	
	public static void employeeMenu(login L) {
		
		int input = -1;
		double funds = 0;
		Scanner scan = new Scanner(System.in);
		accountDAOI ADAO = new accountDAOI();
		loginDAOI LDAO = new loginDAOI();
		userDAOI UDAO = new userDAOI();
		
		while(input != 0) {
			System.out.println(":: Employee Menu ::");
			System.out.println("Press 1 to view accounts, press 2 to view logins, press 3 to view users, press 4 to approve/reject accounts, press 5 to register for account, press 0 to exit.");
			input = scan.nextInt();
			
			// View accounts
			if(input == 1) {
				List<accounts> A = ADAO.selectAllAccounts();
				for(int i = 0; i < A.size(); i++) {
					System.out.println(A.get(i).toString());
				}
				
			// View logins
			} else if(input == 2) {
				List<login> logs = LDAO.selectAllLogin();
				for(int i = 0; i < logs.size(); i++) {
					System.out.println(logs.get(i).toString());
				}
				
			// View users
			} else if(input == 3) {
				List<users> U = UDAO.selectAllUsers();
				for(int i = 0; i < U.size(); i++) {
					System.out.println(U.get(i).toString());
				}
				
			// Approve/reject accounts
			} else if(input == 4) {
				List<accounts> A = ADAO.selectAllAccounts();
				for(int i = 0; i < A.size(); i++) {
					System.out.println(A.get(i).toString());
				}
				System.out.println("Enter account number of which account to approve or reject (press 0 to exit):");
				input = scan.nextInt();
				accounts a = new accounts();
				a = ADAO.selectAccountById(input);
				if(a != null) {
					System.out.println("Press 1 to approve, press 2 to reject, press 0 to exit.");
					input = scan.nextInt();
					if(input == 1) {
						a.setStatus("active");
						ADAO.updateAccount(a);
						System.out.println("Account approved!");
					} else if(input == 2) {
						int id = a.getNum();
						ADAO.deleteAccountById(id);
						System.out.println("Account rejected!");
					} else if(input != 0) {
						System.out.println("Invalid input!");
					}
				} else if(input != 0) {
					System.out.println("Invalid account number!");
				}
				
			// Register for account
			} else if(input == 5) {
				System.out.println("Press 1 to register for checking, press 2 to register for savings, press 0 to exit.");
				input = scan.nextInt();
				if(input == 1) {
					System.out.println("How much is the starting balance?");
					funds = scan.nextDouble();
					if(funds >= 0) {
						accounts a = new accounts();
						List<accounts> A = ADAO.selectAllAccounts();
						a.setNum(A.size() + 1);
						a.setFunds(funds);
						a.setType("checking");
						a.setStatus("active");
						a.setLogin_id(L.getId());
						ADAO.insertIntoAccounts(a);
						L.setType("both");
						LDAO.updateLogin(L);
						System.out.println("Checking account created!");
					} else {
						System.out.println("Invalid amount!");
					}
				} else if(input == 2) {
					System.out.println("How much is the starting balance?");
					funds = scan.nextDouble();
					if(funds >= 0) {
						accounts a = new accounts();
						List<accounts> A = ADAO.selectAllAccounts();
						a.setNum(A.size() + 1);
						a.setFunds(funds);
						a.setType("savings");
						a.setStatus("active");
						a.setLogin_id(L.getId());
						ADAO.insertIntoAccounts(a);
						L.setType("both");
						LDAO.updateLogin(L);
						System.out.println("Savings account created!");
					} else {
						System.out.println("Invalid amount!");
					}
				} else if(input != 0) {
					System.out.println("Invalid input!");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		int input = -1;
		Scanner scan = new Scanner(System.in);
		String username;
		String password;
		loginDAOI LDAO = new loginDAOI();
		login user_login = new login();
/*		
		for(int i = 0; i < logs.size(); i++) {
			System.out.println(logs.get(i).toString());
		}
*/		
		while(input != 0) {
		
			System.out.println("Welcome to the banking app! Press 1 to register customer account, press 0 to exit!");
			System.out.println("Please enter username:");
			username = scan.nextLine();
			System.out.println("Please enter password:");
			password = scan.nextLine();
			
			List<login> logs = LDAO.selectAllLogin();
			for(int i = 0; i < logs.size(); i++) {
				if(logs.get(i).getUsername().equals(username) && logs.get(i).getPassword().equals(password)) {
					user_login = logs.get(i);
					System.out.println(user_login.toString());
				}
			}
			
			if(password.equals("1") || username.equals("1")) {
				System.out.println("Welcome to new customer registration!");
				System.out.println("Please enter username:");
				username = scan.nextLine();
				System.out.println("Please enter password:");
				password = scan.nextLine();
				login customer = new login();
				customer.setId(logs.size() + 1);
				customer.setUsername(username);
				customer.setPassword(password);
				customer.setType("customer");
				LDAO.insertIntoLogin(customer);
				customerMenu(user_login);
			}
			
			if(password.equals("0") || username.equals("0")) {
				System.out.println("Thank you for using our app!");
				System.exit(0);
			}
			
			if(user_login == null) {
				System.out.println("Error! Invalid user!");
				
			} else if(user_login.getType().equals("customer")) {
				customerMenu(user_login);
				
			} else if(user_login.getType().equals("employee")) {
				employeeMenu(user_login);
				
			} else if(user_login.getType().equals("both")) {
				System.out.println("Press 1 for customer menu, press 2 for employee menu.");
				input = scan.nextInt();
				if(input == 1) {
					customerMenu(user_login);
				} else if(input == 2) {
					employeeMenu(user_login);
				}
				
			} else {
				System.out.println("Error! Invalid user type!");
			}
			
		}
	}

}
