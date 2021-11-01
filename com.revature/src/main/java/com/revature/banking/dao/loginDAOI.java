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

public class loginDAOI implements loginDAO {

	@Override
	public boolean insertIntoLogin(login l) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "INSERT INTO bank.login VALUES (DEFAULT, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, l.getUsername());
			ps.setString(2, l.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
				l = new login(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<login> selectAllLogin() {
		Statement stmt = null;
		ResultSet rs = null;
		List<login> llist = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * bank.login";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			llist = new ArrayList<login>();
			while(rs.next()) {
				login l = new login();
				l.setId(rs.getInt(1));
				l.setUsername(rs.getString(2));
				l.setPassword(rs.getString(3));
				llist.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return llist;
	}

	@Override
	public boolean updateLogin(login l) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "UPDATE bank.login SET " + "username=?, " + "passwd=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, l.getUsername());
			ps.setString(2, l.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
		return true;
	}
}
