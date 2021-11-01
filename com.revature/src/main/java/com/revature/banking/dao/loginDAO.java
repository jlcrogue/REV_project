package com.revature.banking.dao;

import java.util.List;

import com.revature.banking.models.login;

public interface loginDAO {
	
	public boolean insertIntoLogin(login l);
	public login selectLoginById(Integer id);
	public List<login> selectAllLogin();
	public boolean updateLogin(login l);
	public boolean deleteLoginById(Integer id);

}
