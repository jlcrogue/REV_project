package com.revature.banking.utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.revature.banking.models.accounts;
import com.revature.banking.dao.accountDAOI;

public class ConnectionTest {
	
	public static void main(String[] args) {
		
		try {
			ConnectionUtility.getConnection();
			System.out.println("A connection was established!");
		} catch(SQLException e) {
			System.out.println("SQL connection error!");
			e.printStackTrace();
		}

	}

}
