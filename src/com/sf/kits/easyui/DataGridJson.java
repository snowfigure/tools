package com.sf.kits.easyui;

import java.util.List;

public class DataGridJson<T> {
	private String total;
	private List<T> rows;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
