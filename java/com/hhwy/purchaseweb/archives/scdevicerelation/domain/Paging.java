package com.hhwy.purchaseweb.archives.scdevicerelation.domain;

public class Paging {
	
	//是否分页
	private boolean paging;
	//页数
	private int page;
	//每页数量
	private int rows;

	public boolean isPaging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
	
	
	
}
