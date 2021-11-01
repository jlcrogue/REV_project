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

public class accountDAOI implements accountDAO {

	@Override
	public boolean insertIntoAccounts(accounts a) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "INSERT INTO bank.accounts VALUES (DEFAULT, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, a.getType());
			ps.setDouble(2, a.getFunds());
			ps.setInt(3, a.getLogin_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public accounts selectAccountById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		accounts a = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * FROM bank.accounts WHERE id=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				a = new accounts(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<accounts> selectAllAccounts() {
		Statement stmt = null;
		ResultSet rs = null;
		List<accounts> alist = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * bank.accounts";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			alist = new ArrayList<accounts>();
			while(rs.next()) {
				accounts a = new accounts();
				a.setNum(rs.getInt(1));
				a.setType(rs.getString(2));
				a.setFunds(rs.getDouble(3));
				a.setLogin_id(rs.getInt(4));
				alist.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alist;
	}

	@Override
	public boolean updateAccount(accounts a) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "UPDATE bank.accounts SET " + "type=?, " + "funds=?, " + "login_i=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, a.getType());
			ps.setDouble(2, a.getFunds());
			ps.setInt(3, a.getLogin_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAccountById(Integer id) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "DELETE FROM bank.accounts WHERE id=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
