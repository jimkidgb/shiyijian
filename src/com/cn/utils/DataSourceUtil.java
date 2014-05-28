package com.cn.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DataSourceUtil {
	public static final int DRUID_MYSQL_SOURCE = 1;
	public static final int C3P0_SOURCE = 2;
	public static final int DBCP_SOURCE = 3;
	public static String confile = "mysql.properties";
	public static Properties p = null;

	static {
		p = new Properties();
		InputStream inputStream = null;
		try {
			confile = DataSourceUtil.class.getClassLoader().getResource("").getPath()+"config"+File.separator+confile;
			File file = new File(java.net.URLDecoder.decode(confile,"UTF-8"));
			inputStream = new BufferedInputStream(new FileInputStream(file));
			p.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				Logger.getLogger(DataSourceUtil.class).error("druid.properties配置文件错误", e);
			}
		}
	}

	public static final DataSource getDataSource(int sourceType) {
		DataSource dataSource = null;
		try {
			switch (sourceType) {
			case DRUID_MYSQL_SOURCE:
				dataSource = DruidDataSourceFactory.createDataSource(p);
				break;
			case C3P0_SOURCE:
				dataSource = DruidDataSourceFactory.createDataSource(p);
				break;
			case DBCP_SOURCE:
				break;
			}
		} catch (Exception e) {
			Logger.getLogger(DataSourceUtil.class).error("获取数据连接失败", e);
		}
		return dataSource;
	}
}
