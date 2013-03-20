package com.xxx.common;

import java.io.Serializable;

/**
 * 分页查询参数及结果出来类
 * 
 * @author Goma OMA1989@YEAH.NET
 * @version v1.0
 * @param <T>
 */
public class Pager<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private T rows;
	private String order;
	private String sort;
	private Integer rowCount;
	private Integer page;
	private Integer total;
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public T getRows() {
		return rows;
	}
	public void setRows(T rows) {
		this.rows = rows;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public Integer getFrom() {
		return (this.page-1)*this.rowCount;
	}
}
