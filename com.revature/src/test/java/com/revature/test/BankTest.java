package com.revature.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.revature.banking.dao.accountDAOI;
import com.revature.banking.dao.loginDAOI;
import com.revature.banking.dao.userDAOI;
import com.revature.banking.models.accounts;
import com.revature.banking.models.login;
import com.revature.banking.models.users;


public class BankTest {
	
	loginDAOI LDAO = new loginDAOI();
	login L1 = LDAO.selectLoginById(1);
	login L2 = LDAO.selectLoginById(2);
	login L3 = LDAO.selectLoginById(1);
	accountDAOI ADAO = new accountDAOI();
	accounts A1 = ADAO.selectAccountById(1);
	accounts A2 = ADAO.selectAccountById(2);
	accounts A3 = ADAO.selectAccountById(1);
	userDAOI UDAO = new userDAOI();
	users U1 = UDAO.selectUserById(1);
	users U2 = UDAO.selectUserById(2);
	users U3 = UDAO.selectUserById(1);
	
	
	
	@Test
	public void test01() {
		assertNotEquals(L1, L2);
	}
	
	@Test
	public void test02() {
		assertEquals(L1, L3);
	}
	
	@Test
	public void test03() {
		assertNotEquals(A1, A2);
	}
	
	@Test
	public void test04() {
		assertEquals(A1, A3);
	}
	
	@Test
	public void test05() {
		assertNotEquals(U1, U2);
	}
	
	@Test
	public void test06() {
		assertEquals(U1, U3);
	}
	
	@Test
	public void test07() {
		users u = UDAO.selectUserById(4);
		UDAO.deleteUserById(4);
		assertNull(UDAO.selectUserById(4));
		UDAO.insertIntoUsers(u);
	}
	
	@Test
	public void test08() {
		accounts a = ADAO.selectAccountById(6);
		ADAO.deleteAccountById(6);
		assertNull(ADAO.selectAccountById(6));
		ADAO.insertIntoAccounts(a);
	}
	
	@Test
	public void test09() {
		accounts a = ADAO.selectAccountById(6);
		accounts b = new accounts();
		b = a;
		b.setStatus("pending");
		ADAO.updateAccount(b);
		assertNotEquals(a, b);
		ADAO.updateAccount(a);
	}

	@Test
	public void test10() {
		accounts a = ADAO.selectAccountById(6);
		accounts b = new accounts();
		b = a;
		b.setStatus("active");
		ADAO.updateAccount(b);
		assertNotEquals(a, b);
		ADAO.updateAccount(a);
	}

}
