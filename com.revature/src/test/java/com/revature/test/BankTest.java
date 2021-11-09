package com.revature.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.revature.banking.dao.accountDAOI;
import com.revature.banking.dao.loginDAOI;
import com.revature.banking.models.accounts;
import com.revature.banking.models.login;


public class BankTest {
	
	loginDAOI LDAO = new loginDAOI();
	login L1 = LDAO.selectLoginById(1);
	login L2 = LDAO.selectLoginById(2);
	login L3 = LDAO.selectLoginById(1);
	accountDAOI ADAO = new accountDAOI();
	accounts A1 = ADAO.selectAccountById(1);
	accounts A2 = ADAO.selectAccountById(2);
	accounts A3 = ADAO.selectAccountById(1);
	

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

}
