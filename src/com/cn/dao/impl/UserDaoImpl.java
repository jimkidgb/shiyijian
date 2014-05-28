package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.cn.dao.IUserDao;
import com.cn.model.platform.User;
import com.cn.model.platform.UserRole;
import com.cn.utils.DbUtils;

public class UserDaoImpl extends DbUtils implements IUserDao {

	@Override
	public boolean addUser(User u) {
		boolean b = true;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		QueryRunner qr = null;
		String sql = "insert into t_platform_users values(null,?,?,?,?,?,?,?,now(),now(),?,?,?,?,?)";
		Object[] params = new Object[] { u.getUsername(), u.getPassword(), u.getRealname(), u.getMobile(), u.getEmail(), u.getAddress(), u.getRemarks(), u.getModifier(), u.getGroupid(), "0", u.getLastlogindate(), u.getLastip() };
		try {
			int key = 0;
			qr = new QueryRunner();
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}
			sql = "insert into t_platform_userrole values(null,?,?,now(),?,now())";
			String[] s = u.getUserrole();
			Object[][] param = new Object[s.length][3];
			for (int i = 0; i < s.length; i++) {
				param[i][0] = key;
				param[i][1] = s[i];
				param[i][2] = u.getModifier();
			}
			qr.batch(conn, sql, param);
			conn.commit();
		} catch (Exception e) {
			rollback(conn);
			b = false;
			e.printStackTrace();
		} finally {
			close(rs, stmt, conn);
		}
		return b;
	}

	@Override
	public boolean editUser(User u) {
		String sql = "update t_platform_users set password=?,realname=?,mobile=?,email=?,address=?,remarks=?,modifier=?,modifydate=now() where id=?";
		Object[] params = new Object[] { u.getPassword(), u.getRealname(), u.getMobile(), u.getEmail(), u.getAddress(), u.getRemarks(), u.getModifier(), u.getId() };
		Connection conn = null;
		boolean b = true;
		QueryRunner qr = null;
		try {
			qr = new QueryRunner();
			conn = getConnection();
			conn.setAutoCommit(false);
			qr.update(conn, sql, params);
			sql = "delete from t_platform_userrole where userid =?";
			qr.update(conn, sql, u.getId());
			sql = "insert into t_platform_userrole values(null,?,?,now(),?,now())";
			String[] s = u.getUserrole();
			Object[][] param = new Object[s.length][3];
			for (int i = 0; i < s.length; i++) {
				param[i][0] = u.getId();
				param[i][1] = s[i];
				param[i][2] = u.getModifier();
			}
			qr.batch(conn, sql, param);
			conn.commit();
		} catch (Exception e) {
			b = false;
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(null, null, conn);
		}
		return b;
	}

	@Override
	public User getUser(String username) {
		String sql = "select * from t_platform_users where username = ?";
		return findFirst(User.class, sql, username);
	}

	@Override
	public String getUserCount(String realname, String username, String groupid) {
		String sql = "select count(*) from t_platform_users where groupid=? ";
		if (realname != null && !realname.equals("")) {
			sql += " and realname like '%" + realname + "%'";
		}
		if (username != null && !username.equals("")) {
			sql += " and username like '%" + username + "%'";
		}
		return findBy(sql, 1, groupid).toString();
	}

	@Override
	public List<User> getUserList(String realname, String username, String groupid, Integer page, Integer rows) {
		String sql = "select * from t_platform_users where groupid=? ";
		if (realname != null && !realname.equals("")) {
			sql += " and realname like '%" + realname + "%'";
		}
		if (username != null && !username.equals("")) {
			sql += " and username like '%" + username + "%'";
		}
		sql += " order by id desc limit ?,? ";
		return find(User.class, sql, new Object[] { groupid, (page - 1) * rows, rows });
	}

	@Override
	public boolean removeUser(String id) {
		String sql = "delete from t_platform_users where id = ?";
		Connection conn = null;
		boolean b = true;
		QueryRunner qr = null;
		try {
			qr = new QueryRunner();
			conn = getConnection();
			conn.setAutoCommit(false);
			qr.update(conn, sql, id);
			sql = "delete from t_platform_userrole where userid = ?";
			qr.update(conn, sql, id);
			conn.commit();
		} catch (Exception e) {
			b = false;
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(null, null, conn);
		}
		return b;
	}

	@Override
	public List<User> getUserList() {
		String sql = "select * from t_platform_users";
		return find(User.class, sql);
	}

	@Override
	public List<UserRole> getUserRole(String userid) {
		String sql = "select * from t_platform_userrole where userid = ?";
		return find(UserRole.class, sql, userid);
	}

	@Override
	public boolean updateStatus(String id, String status) {
		String sql = "update t_platform_users set status = ? where id = ?";
		return update(sql, new Object[] { status, id });
	}

	@Override
	public List<User> getUserList(String groupid) {
		String sql = "select * from t_platform_users where groupid = ?";
		return find(User.class, sql, groupid);
	}
}
