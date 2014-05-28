package com.cn.dao.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import com.cn.dao.IActDao;
import com.cn.model.wx.Act;
import com.cn.model.wx.Export;
import com.cn.utils.DbUtils;

public class ActDaoImpl extends DbUtils implements IActDao {

	@Override
	public boolean addAct(Act a) {
		String sql = "insert into t_act values(null,?,?,?,?,?,now(),'0')";
		Object[] params = new Object[] { a.getName(), a.getStartdate(),
				a.getEnddate(), a.getUrl(), a.getModifier() };
		return insert(sql, params);
	}

	@Override
	public boolean editAct(Act a) {
		String sql = "update t_act set name = ?,startdate=?,enddate=?,modifier=?,modifydate=now() where id = ?";
		Object[] params = new Object[] { a.getName(), a.getStartdate(),
				a.getEnddate(), a.getModifier(), a.getId() };
		return update(sql, params);
	}

	@Override
	public boolean editStatus(String id, String status) {
		String sql = "update t_act set status = ? where id = ?";
		return update(sql, new Object[] { status, id });
	}

	@Override
	public String getActCount(Act a) {
		String sql = "select count(*) from t_act where 1=1";
		if (a.getName() != null && !a.getName().equals("")) {
			sql += " and name like '%" + a.getName() + "%' ";
		}
		return findBy(sql, 1).toString();
	}

	@Override
	public List<Act> getActList(Act a, Integer page, Integer rows) {
		String sql = "select * from t_act where 1=1";
		if (a.getName() != null && !a.getName().equals("")) {
			sql += " and name like '%" + a.getName() + "%' ";
		}
		sql += " limit ?,?";
		return find(Act.class, sql, new Object[] { (page - 1) * rows, rows });
	}

	@Override
	public boolean removeAct(String id) {
		String sql = "delete from t_act where id = ?";
		return update(sql, id);
	}

	@Override
	public void addSQL(String inPath, String outPath) {
		ResourceBundle rb = ResourceBundle.getBundle("config.mysql");
		SQLExec sqlExec = new SQLExec();
		sqlExec.setDriver(rb.getString("driverClassName"));
		sqlExec.setUrl(rb.getString("url"));
		sqlExec.setUserid(rb.getString("username"));
		sqlExec.setPassword(rb.getString("password"));
		sqlExec.setSrc(new File(inPath));
		sqlExec.setPrint(true);
		sqlExec.setProject(new Project());
		sqlExec.setOutput(new File(outPath));
		sqlExec.execute();
	}

	@Override
	public List<Export> getExport() {
		String sql = "select * from t_act_export";
		return find(Export.class, sql);
	}

	public static void main(String[] args) throws Exception, Exception {
		String sql = "select * from t_act_export";
		DbUtils db = new DbUtils();
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet columnSet = null;
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			DatabaseMetaData dmd = conn.getMetaData();
			ResultSetMetaData rsmd = rs.getMetaData();
			columnSet = dmd.getColumns(null, "%", rsmd.getTableName(1), "%");
			sb.append("[");
			while (rs.next()) {
				sb.append("{");
				System.out.println(columnSet);
				while(columnSet.next()){
					sb.append("\"" + columnSet.getString("COLUMN_NAME") + "\":" + "\"" + rs.getObject(columnSet.getString("COLUMN_NAME")) + "\",");
				}
				sb.delete(sb.length() - 1, sb.length());
				sb.append("},");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append("]");
			System.out.println(sb);
		} catch (Exception e) {
			e.printStackTrace();
			db.close(columnSet, null, null);
			sb = new StringBuffer();
		} finally {
			db.close(rs, stmt, conn);
		}
	}

	@Override
	public String getColumnJson(String sql) {
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet columnSet = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			DatabaseMetaData dmd = conn.getMetaData();
			ResultSetMetaData rsmd = rs.getMetaData();
			columnSet = dmd.getColumns(null, "%", rsmd.getTableName(1), "%");
			sb.append("[");
			while(columnSet.next()){
				sb.append("{");
				sb.append("\"field\":\""+columnSet.getString("COLUMN_NAME")+"\",");
				if(columnSet.getString("REMARKS")!=null && !columnSet.getString("REMARKS").equals("")){
					sb.append("\"title\":\""+columnSet.getString("REMARKS")+"\",");
				}else{
					sb.append("\"title\":\""+columnSet.getString("COLUMN_NAME")+"\",");
				}
				sb.append("\"align\":\"center\",");
				sb.append("\"width\":100");
				sb.append("},");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append("]");
		} catch (Exception e) {
			e.printStackTrace();
			sb = new StringBuffer();
		} finally {
			close(columnSet,null,null);
			close(rs, stmt, conn);
		}
		return sb.toString();
	}

	@Override
	public String getDataJson(String sql) {
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			sb.append("[");
			while (rs.next()) {
				sb.append("{");
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					sb.append("\"" + rsmd.getColumnName(i) + "\":" + "\"" + rs.getObject(rsmd.getColumnName(i)) + "\",");
				}
				sb.delete(sb.length() - 1, sb.length());
				sb.append("},");
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append("]");
		} catch (Exception e) {
			e.printStackTrace();
			sb = new StringBuffer();
		} finally {
			close(rs, stmt, conn);
		}
		return sb.toString();
	}
	
	@Override
	public Export getExport(String id) {
		String sql = "select * from t_act_export where id = ?";
		return findFirst(Export.class, sql, id);
	}
}
