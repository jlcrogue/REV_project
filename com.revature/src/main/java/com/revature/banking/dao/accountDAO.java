package com.revature.banking.dao;

import java.util.List;

import com.revature.banking.models.accounts;

public interface accountDAO {

	public boolean insertIntoAccounts(accounts a);
	public accounts selectAccountById(Integer id);
	public List<accounts> selectAllAccounts();
	public boolean updateAccount(accounts a);
	public boolean deleteAccountById(Integer id);
	
}
