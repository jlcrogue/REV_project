package com.revature.banking.dao;

import java.util.List;
import com.revature.banking.models.users;

public interface userDAO {
	
	public boolean insertIntoUsers(users u);
	public users selectUserById(Integer id);
	public List<users> selectAllUsers();
	public boolean updateUser(users u);
	public boolean deleteUserById(Integer id);
	
}
