package com.sist.last.cmn;

public class SearchScrap extends DTO {

	//페이지 사이즈
	private int pageSize;
		
	//페이지 num
	private int pageNum;
	
	
	private String loginId;
	
	public SearchScrap() {}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public String toString() {
		return "SearchScrap [pageSize=" + pageSize + ", pageNum=" + pageNum + ", housesFk=" + ", loginId="
				+ loginId + ", toString()=" + super.toString() + "]";
	}


	
	
	
	
}
