package com.sist.last.cmn;

public class SearchReview extends DTO {

	private int pageSize;// 페이지 사이즈
	private int pageNum;// 페이지 num

	private String reviewFk;// 상품/집들이/q&a fk값

	public SearchReview() {

	}

	public SearchReview(int pageSize, int pageNum, String reviewFk) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.reviewFk = reviewFk;
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

	public String getReviewFk() {
		return reviewFk;
	}

	public void setReviewFk(String reviewFk) {
		this.reviewFk = reviewFk;
	}

	@Override
	public String toString() {
		return "Search [pageSize=" + pageSize + ", pageNum=" + pageNum + ", reviewFk=" + reviewFk + "]";
	}

}
