package com.cn.viewmodel;

import java.util.List;

public class DataGrid<T> {

	private String total;
	private List<T> rows;

	public DataGrid() {
	}

	public String getTotal() {
		return total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
