package com.cn.utils;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExportUtils extends DbUtils {

	public boolean exportExcel(String sql, Integer sheetCount, String filePath) {
		boolean b = false;
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		Workbook workbook = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			DatabaseMetaData dmd = conn.getMetaData();
			ResultSet columnSet = dmd.getColumns(null, "%", rsmd.getTableName(1), "%");
			List<String> list = new ArrayList<String>();
			while(columnSet.next()){
				if(columnSet.getString("REMARKS")!=null && !columnSet.getString("REMARKS").equals("")){
					list.add(columnSet.getString("REMARKS"));
				}else{
					list.add(columnSet.getString("COLUMN_NAME"));
				}
			}
			columnSet.close();
			workbook = new SXSSFWorkbook(100);
			Sheet sheet = null;
			Row row = null;
			int index = 0;
			while (rs.next()) {
				if (index % sheetCount == 0) {
					sheet = workbook.createSheet("sheet" + ((index / sheetCount) + 1));
					row = sheet.createRow(0);
					for (int i = 1; i <= list.size(); i++) {
						row.createCell(i-1).setCellValue(list.get(i-1));
					}
					row = sheet.createRow((index % sheetCount)+1);
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						Object obj = rs.getObject(rsmd.getColumnName(i));
						row.createCell(i-1).setCellValue(obj==null?"":obj.toString());
					}
				} else {
					row = sheet.createRow((index % sheetCount)+1);
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						Object obj = rs.getObject(rsmd.getColumnName(i));
						row.createCell(i-1).setCellValue(obj==null?"":obj.toString());
					}
				}
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, stmt, conn);
		}
		if (workbook != null) {
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(filePath);
				workbook.write(fos);
				fos.close();
				b = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}
}
