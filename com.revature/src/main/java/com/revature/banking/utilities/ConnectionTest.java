package com.revature.banking.utilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import com.revature.banking.models.accounts;
import com.revature.banking.models.login;
import com.revature.banking.models.users;
import com.revature.banking.dao.accountDAOI;
import com.revature.banking.dao.loginDAOI;
import com.revature.banking.dao.userDAOI;

public class ConnectionTest {
	
	public static void main(String[] args) {
		
		try {
			ConnectionUtility.getConnection();
			System.out.println("A connection was established!");
		} catch(SQLException e) {
			System.out.println("SQL connection error!");
			e.printStackTrace();
		}
		
		loginDAOI LDAO = new loginDAOI();
		accountDAOI ADAO = new accountDAOI();
		userDAOI UDAO = new userDAOI();
/*	
		login L1 = LDAO.selectLoginById(1);
		login L2 = LDAO.selectLoginById(2);
		login L3 = LDAO.selectLoginById(1);
		List<users> Users = UDAO.selectAllUsers();
		accounts A1 = ADAO.selectAccountById(1);
		accounts A2 = ADAO.selectAccountById(2);
		accounts A3 = ADAO.selectAccountById(3);
		accounts A4 = ADAO.selectAccountById(4);
		List<accounts> Accounts = ADAO.selectAllAccounts();

		System.out.println(L1.toString());
		System.out.println(L2.toString());
		System.out.println(L3.toString());
		System.out.println(A1.toString());
		System.out.println(A2.toString());
		System.out.println(A3.toString());
		System.out.println(A4.toString());
		A2.setFunds(35.01);
		ADAO.updateAccount(A2);
		System.out.println(A2.toString());
		for(int i = 0; i < Accounts.size(); i++) {
			System.out.println(Accounts.get(i).toString());
		}
		for(int i = 0; i < Users.size(); i++) {
			System.out.println(Users.get(i).toString());
		}
	
		login L4 = new login();
		login L5 = new login();
		L4.setId(3);
		L4.setUsername("BankerSupreme");
		L4.setPassword("Shekels");
		L4.setType("employee");
		
		L5.setId(4);
		L5.setUsername("Kuomintang");
		L5.setPassword("reunification");
		L5.setType("both");
		LDAO.insertIntoLogin(L4);
		LDAO.insertIntoLogin(L5);
				
		List<login> logs = LDAO.selectAllLogin();
		for(int i = 0; i < logs.size(); i++) {
			System.out.println(logs.get(i).toString());
		}

		users u1 = new users();
		users u2 = new users();
		u1.setId(3);
		u1.setFname("Evelyn");
		u1.setMname("Robert Adrian");
		u1.setLname("Rothschild");
		u1.setSsn(313336663);
		Date dob1 = new Date(1931, 8, 29);
		u1.setDob(dob1);
		u1.setLogin_id(3);
		
		u2.setId(4);
		u2.setFname("Kai");
		u2.setMname("Shek");
		u2.setLname("Chiang");
		u2.setSsn(666421337);
		Date dob2 = new Date(1887, 10, 31);
		u2.setDob(dob2);
		u2.setLogin_id(4);
		
		UDAO.insertIntoUsers(u1);
		UDAO.insertIntoUsers(u2);
		List<users> Users = UDAO.selectAllUsers();
		for(int i = 0; i < Users.size(); i++) {
			System.out.println(Users.get(i).toString());
		}

		accounts a1 = new accounts();
		accounts a2 = new accounts();
		a1.setNum(5);
		a1.setType("checking");
		a1.setFunds(500000.00);
		a1.setLogin_id(4);
		a2.setNum(6);
		a2.setType("savings");
		a2.setFunds(1000000.00);
		a2.setLogin_id(4);
		
		ADAO.insertIntoAccounts(a1);
		ADAO.insertIntoAccounts(a2);
		List<accounts> Accounts = ADAO.selectAllAccounts();
		for(int i = 0; i < Accounts.size(); i++) {
			System.out.println(Accounts.get(i).toString());
		}
*/
		accounts a = ADAO.selectAccountById(6);
		a.setStatus("active");
		ADAO.updateAccount(a);
	}

}
