package com.cn.utils;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
public class DbUtils {
	
	private static DataSource dataSource = DataSourceUtil.getDataSource(1);
	private QueryRunner queryRunner;
	private Logger logger = Logger.getLogger(this.getClass());

	public DbUtils(String prefix) {
	}

	public DbUtils() {
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * 插入记录
	 * 
	 * @param sql
	 *            插入sql语句
	 * @param params
	 *            插入参数
	 * @return 返回插入是否成功
	 */
	public boolean insert(String sql, Object[] params) {
		queryRunner = new QueryRunner(dataSource);
		int affectedRows = 0;
		try {
			if (params == null) {
				affectedRows = queryRunner.update(sql);
			} else {
				affectedRows = queryRunner.update(sql, params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("插入失败:" + e);
		}
		return affectedRows > 0 ? true : false;
	}

	/**
	 * 批量更新
	 * 
	 * @param sql
	 */
	public void batchUpdataStmt(List<String> sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for (int i = 0; i < sql.size(); i++) {
				stmt.execute(sql.get(i));
			}
			conn.commit();
		} catch (SQLException e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}
	}

	/**
	 * 批量更新
	 * 
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	public void batchUpdatePs(String sql, Object[][] params)throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for (int i = 0; i < params.length; i++) {
				for (int j = 0; j < params[i].length; j++) {
					ps.setObject(j + 1, params[i][j]);
				}
				ps.addBatch();
				if (i % 500 == 0) {
					ps.executeBatch();
					conn.commit();
					ps.clearBatch();
				}
			}
			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			close(ps, conn);
		}
	}

	/**
	 * 插入数据库，返回自动增长的主键
	 * 
	 * @param sql
	 *            执行的sql语句
	 * @param params
	 *            执行参数
	 * @return 主键 注意；此方法没关闭资源
	 */
	public int insertForKeys(String sql, Object[] params) {
		int key = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ParameterMetaData pmd = stmt.getParameterMetaData();
			if (params.length < pmd.getParameterCount()) {
				throw new SQLException("parameter error:"+ pmd.getParameterCount());
			}
			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("插入返回主键失败：" + sql, e);
		} finally {
			close(rs, stmt, conn);
		}
		return key;
	}

	/**
	 * 单条修改记录
	 * 
	 * @param sql
	 *            语句
	 * @param params
	 *            参数数组
	 * @return 受影响的行数
	 */
	public boolean update(String sql, Object[] params) {
		queryRunner = new QueryRunner(dataSource);
		int affectedRows = 0;
		try {
			if (params == null) {
				affectedRows = queryRunner.update(sql);
			} else {
				affectedRows = queryRunner.update(sql, params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("修改失败：" + sql, e);
		}
		return affectedRows > 0 ? true : false;
	}

	/**
	 * 单条修改记录
	 * 
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 受影响的行数
	 */
	public boolean update(String sql, Object param) {
		return update(sql, new Object[] { param });
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 *            sql语句
	 * @return 受影响的行数
	 */
	public boolean update(String sql) {
		return update(sql, null);
	}

	/**
	 * 批量修改记录
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            二维参数数组
	 * @return 受影响的行数的数组
	 */
	public int[] batchUpdate(String sql, Object[][] params) {
		queryRunner = new QueryRunner(dataSource);
		int[] affectedRows = new int[0];
		try {
			affectedRows = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("批量插入失败：" + sql, e);
		}
		return affectedRows;
	}
	/**
	 * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @return 查询结果
	 */
	public <T> List<T> find(Class<T> entityClass, String sql) {
		return find(entityClass, sql, null);
	}

	/**
	 * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 查询结果
	 */
	public <T> List<T> find(Class<T> entityClass, String sql, Object param) {
		return find(entityClass, sql, new Object[] { param });
	}

	/**
	 * 执行查询，将每行的结果保存到Bean中，然后将所有Bean保存到List中
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 查询结果
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(Class<T> entityClass, String sql, Object[] params) {
		queryRunner = new QueryRunner(dataSource);
		List<T> list = new ArrayList<T>();
		try {
			if (params == null) {
				list = (List<T>) queryRunner.query(sql, new BeanListHandler(entityClass));
			} else {
				list = (List<T>) queryRunner.query(sql, new BeanListHandler(entityClass), params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("查询失败:", e);
		}
		return list;
	}

	/**
	 * 获取列拼接成字符串
	 * 
	 * @param sql
	 * @return
	 */
	public String findColumn(String sql) {
		StringBuffer sb = new StringBuffer();
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (!sb.toString().equals("")) {
					sb.append(",");
				}
				sb.append(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt, conn);
		}
		return sb.toString();
	}

	public ResultSet getResultSet(String sql, Connection conn) throws Exception {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
		return rs;
	}
	/**
	 * 查询出结果集中的第一条记录，并封装成对象
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @return 对象
	 */
	public <T> T findFirst(Class<T> entityClass, String sql) {
		return findFirst(entityClass, sql, null);
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成对象
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 对象
	 */
	public <T> T findFirst(Class<T> entityClass, String sql, Object param) {
		return findFirst(entityClass, sql, new Object[] { param });
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成对象
	 * 
	 * @param entityClass
	 *            类名
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T findFirst(Class<T> entityClass, String sql, Object[] params) {
		queryRunner = new QueryRunner(dataSource);
		Object object = null;
		try {
			if (params == null) {
				object = queryRunner.query(sql, new BeanHandler(entityClass));
			} else {
				object = queryRunner.query(sql, new BeanHandler(entityClass),params);
			}
		} catch (SQLException e) {
			logger.error("返回第一条数据失败:" + e.getMessage());
			e.printStackTrace();
		}
		return (T) object;
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成Map对象
	 * 
	 * @param sql
	 *            sql语句
	 * @return 封装为Map的对象
	 */
	public Map<String, Object> findFirst(String sql) {
		return findFirst(sql, null);
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成Map对象
	 * 
	 * @param sql
	 *            sql语句
	 * @param param
	 *            参数
	 * @return 封装为Map的对象
	 */
	public Map<String, Object> findFirst(String sql, Object param) {
		return findFirst(sql, new Object[] { param });
	}

	/**
	 * 查询出结果集中的第一条记录，并封装成Map对象
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 封装为Map的对象
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findFirst(String sql, Object[] params) {
		queryRunner = new QueryRunner(dataSource);
		Map<String, Object> map = null;
		try {
			if (params == null) {
				map = queryRunner.query(sql,new MapHandler());
			} else {
				map = queryRunner.query(sql,new MapHandler(), params);
			}
		} catch (SQLException e) {
			logger.error("返回第一条数据失败:" , e);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnName
	 *            列名
	 * @return 结果对象
	 */
	public Object findBy(String sql, String params) {
		return findBy(sql, params, null);
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnName
	 *            列名
	 * @param param
	 *            参数
	 * @return 结果对象
	 */
	public Object findBy(String sql, String columnName, Object param) {
		return findBy(sql, columnName, new Object[] { param });
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnName
	 *            列名
	 * @param params
	 *            参数数组
	 * @return 结果对象
	 */
	@SuppressWarnings("unchecked")
	public Object findBy(String sql, String columnName, Object[] params) {
		queryRunner = new QueryRunner(dataSource);
		Object object = null;
		try {
			if (params == null) {
				object = queryRunner.query(sql, new ScalarHandler(columnName));
			} else {
				object = queryRunner.query(sql, new ScalarHandler(columnName),params);
			}
		} catch (SQLException e) {
			logger.error("返回第一条数据失败：" , e);
		}
		return object;
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnIndex
	 *            列索引
	 * @return 结果对象
	 */
	public Object findBy(String sql, int columnIndex) {
		return findBy(sql, columnIndex, null);
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnIndex
	 *            列索引
	 * @param param
	 *            参数
	 * @return 结果对象
	 */
	public Object findBy(String sql, int columnIndex, Object param) {
		return findBy(sql, columnIndex, new Object[] { param });
	}

	/**
	 * 查询某一条记录，并将指定列的数据转换为Object
	 * 
	 * @param sql
	 *            sql语句
	 * @param columnIndex
	 *            列索引
	 * @param params
	 *            参数数组
	 * @return 结果对象
	 */
	@SuppressWarnings("unchecked")
	public Object findBy(String sql, int columnIndex, Object[] params) {
		queryRunner = new QueryRunner(dataSource);
		Object object = null;
		try {
			if (params == null) {
				object = queryRunner.query(sql, new ScalarHandler(columnIndex));
			} else {
				object = queryRunner.query(sql, new ScalarHandler(columnIndex),params);
			}
		} catch (SQLException e) {
			logger.error("返回第一条数据失败：", e);
		}
		return object;
	}
	/**
	 * 关闭连接
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(PreparedStatement ps, Connection conn) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
