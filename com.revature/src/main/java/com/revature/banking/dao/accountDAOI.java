package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.banking.models.accounts;
import com.revature.banking.utilities.ConnectionUtility;
import com.revature.logging.LogSimulator;

public class accountDAOI implements accountDAO {

	@Override
	public boolean insertIntoAccounts(accounts a) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "INSERT INTO bank.accounts VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, a.getNum());
			ps.setString(2, a.getType());
			ps.setDouble(3, a.getFunds());
			ps.setInt(4, a.getLogin_id());
			ps.setString(5, a.getStatus());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("Account: " + a.getType() + " inserted!");
		return true;
	}

	@Override
	public accounts selectAccountById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		accounts a = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * FROM bank.accounts WHERE num=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				a = new accounts(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LogSimulator.getLogger().trace("Account ID: " + id + " retrieved!");
		return a;
	}

	@Override
	public List<accounts> selectAllAccounts() {
		Statement stmt = null;
		ResultSet rs = null;
		List<accounts> alist = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * FROM bank.accounts";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			alist = new ArrayList<accounts>();
			while(rs.next()) {
				accounts a = new accounts();
				a.setNum(rs.getInt(1));
				a.setType(rs.getString(2));
				a.setFunds(rs.getDouble(3));
				a.setLogin_id(rs.getInt(4));
				a.setStatus(rs.getString(5));
				alist.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LogSimulator.getLogger().trace("All accounts retrieved!");
		return alist;
	}

	@Override
	public boolean updateAccount(accounts a) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "UPDATE bank.accounts SET " + "type=?, " + "funds=?, " + "login_id=?, " + "status=? " + "WHERE num=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, a.getType());
			ps.setDouble(2, a.getFunds());
			ps.setInt(3, a.getLogin_id());
			ps.setString(4, a.getStatus());
			ps.setInt(5, a.getNum());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("Account: " + a.getType() + " updated!");
		return true;
	}

	@Override
	public boolean deleteAccountById(Integer id) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "DELETE FROM bank.accounts WHERE num=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("Account: " + id + " deleted!");
		return true;
	}
}
