package com.sist.last.cmn;

public class SearchPay extends DTO {
	
	//member_id
	private String memberId;
	
	//페이시 사이즈
	private int pageSize;
	
	//페이지 번호
	private int pageNum;
	
	public SearchPay() {}

	public SearchPay(String memberId, int pageSize, int pageNum) {
		super();
		this.memberId = memberId;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

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

	@Override
	public String toString() {
		return "Search [memberId=" + memberId + ", pageSize=" + pageSize + ", pageNum=" + pageNum + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
