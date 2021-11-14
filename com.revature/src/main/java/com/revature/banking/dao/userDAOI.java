package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.revature.banking.models.users;
import com.revature.banking.utilities.ConnectionUtility;
import com.revature.logging.LogSimulator;

public class userDAOI implements userDAO {

	@Override
	public boolean insertIntoUsers(users u) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "INSERT INTO bank.users VALUES (?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getFname());
			ps.setString(3, u.getMname());
			ps.setString(4, u.getLname());
			ps.setInt(5, u.getSsn());
			ps.setDate(6, u.getDob());
			ps.setInt(7, u.getLogin_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("User: " + u.getFname() + " " + u.getLname() + " inserted!");
		return true;
	}

	@Override
	public users selectUserById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		users u = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * FROM bank.users WHERE id=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				u = new users(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LogSimulator.getLogger().trace("User ID: " + id + " retrieved!");
		return u;
	}

	@Override
	public List<users> selectAllUsers() {
		Statement stmt = null;
		ResultSet rs = null;
		List<users> ulist = null;
		try(Connection conn = ConnectionUtility.getConnection()) {
			String query = "SELECT * FROM bank.users";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			ulist = new ArrayList<users>();
			while(rs.next()) {
				users u = new users();
				u.setId(rs.getInt(1));
				u.setFname(rs.getString(2));
				u.setMname(rs.getString(3));
				u.setLname(rs.getString(4));
				u.setSsn(rs.getInt(5));
				u.setDob(rs.getDate(6));
				u.setLogin_id(rs.getInt(7));
				ulist.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LogSimulator.getLogger().trace("All users retrieved!");
		return ulist;
	}

	@Override
	public boolean updateUser(users u) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "UPDATE bank.users SET " + "fname=?, " + "mname=?, " + "lname=?, " 
					+ "ssn=?, " + "dob=?, " + "login_id=? " + "WHERE id=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, u.getFname());
			ps.setString(2, u.getMname());
			ps.setString(3, u.getLname());
			ps.setInt(4, u.getSsn());
			ps.setDate(5, u.getDob());
			ps.setInt(6, u.getLogin_id());
			ps.setInt(7, u.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("User: " + u.getFname() + " " + u.getLname() + " updated!");
		return true;
	}

	@Override
	public boolean deleteUserById(Integer id) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtility.getConnection()) {
			String query = "DELETE FROM bank.users WHERE id=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		LogSimulator.getLogger().trace("User: " + id + " deleted!");
		return true;
	}
	
}
