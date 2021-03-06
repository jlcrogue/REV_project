package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.revature.banking.models.login;
import com.revature.banking.utilities.ConnectionUtility;
import com.revature.logging.LogSimulator;

public class loginDAOI implements loginDAO {

	@Override
	public boolean insertIntoLogin(login l) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "INSERT INTO bank.login VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, l.getId());
			ps.setString(2, l.getUsername());
			ps.setString(3, l.getPassword());
			ps.setString(4, l.getType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("Login: " + l.getUsername() + " inserted!");
		return true;
	}

	@Override
	public login selectLoginById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		login l = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * FROM bank.login WHERE id=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				l = new login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LogSimulator.getLogger().trace("Login ID: " + id + " retrieved!");
		return l;
	}

	@Override
	public List<login> selectAllLogin() {
		Statement stmt = null;
		ResultSet rs = null;
		List<login> llist = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * FROM bank.login";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			llist = new ArrayList<login>();
			while(rs.next()) {
				login l = new login();
				l.setId(rs.getInt(1));
				l.setUsername(rs.getString(2));
				l.setPassword(rs.getString(3));
				l.setType(rs.getString(4));
				llist.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LogSimulator.getLogger().trace("All logins retrieved!");
		return llist;
	}

	@Override
	public boolean updateLogin(login l) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "UPDATE bank.login SET " + "username=?, " + "password=?, " + "type=? " + "WHERE id=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, l.getUsername());
			ps.setString(2, l.getPassword());
			ps.setString(3, l.getType());
			ps.setInt(4, l.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("Login: " + l.getUsername() + " updated!");
		return true;
	}

	@Override
	public boolean deleteLoginById(Integer id) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "DELETE FROM bank.login WHERE id=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("Login: " + id + " deleted!");
		return true;
	}
}
