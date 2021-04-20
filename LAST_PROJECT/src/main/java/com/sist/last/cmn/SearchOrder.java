package com.sist.last.cmn;
public class SearchOrder extends DTO {
	//검색 구분 : 태그(10), 제목(20)
	private String searchDiv;
	//검색어
	private String searchWord;
	//페이지 size (10, 20, 30, ...)
	private int pageSize;
	//페이지 num (1, 2, 3, ...)
	private int pageNum;
	//정렬 구분 : 최신순(30), 인기순(40)
	private String orderDiv;
	
	public SearchOrder() {
	}
	
	public SearchOrder(String searchDiv, String searchWord, int pageSize, int pageNum, String orderDiv) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.orderDiv = orderDiv;
	}
	public String getSearchDiv() {
		return searchDiv;
	}
	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
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
	public String getOrderDiv() {
		return orderDiv;
	}
	public void setOrderDiv(String orderDiv) {
		this.orderDiv = orderDiv;
	}
	@Override
	public String toString() {
		return "Search [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", pageSize=" + pageSize + ", pageNum="
				+ pageNum + ", orderDiv=" + orderDiv + ", toString()=" + super.toString() + "]";
	}
}
