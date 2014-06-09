package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbutils.QueryRunner;

import com.cn.dao.IPicDao;
import com.cn.model.platform.User;
import com.cn.model.shiyijian.Pic;
import com.cn.utils.DbUtils;

public class PicDaoImpl extends DbUtils implements IPicDao{

	@Override
	public String addpic(String uplodpath, String type) {
		String id = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		QueryRunner qr = null;
		String sql = "insert into t_client_pic values(null,?,?,now())";
		type = changeType(type);
		Object[] params = new Object[] {uplodpath,type};
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
				id = String.valueOf(key);
			}
			conn.commit();
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(rs, stmt, conn);
		}		
		return id;
	}

	private String changeType(String type) {
		String flag = "";
		if(".jpg".indexOf(type) != -1){
			flag = "jpg";
		}else{
			flag = "png";
		}

		return flag;
	}

	@Override
	public Pic getpic(String id) {
		String sql = "select * from t_client_pic where id = ?";
		return findFirst(Pic.class, sql, id);
	}

	
	
	
}
