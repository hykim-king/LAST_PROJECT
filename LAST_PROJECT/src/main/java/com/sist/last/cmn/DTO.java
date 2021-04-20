package com.sist.last.cmn;
/**
 * 모든 VO의 Parent
 * @author gockd
 *
 */
public class DTO {
	
	//글 번호
	private int num;
	//총글수
	private int totalCnt;
	
	
	//-----------------HousesDao-------------------------- 
	private int totalScrap;//총 스크랩 수
	//-----------------//HousesDao--------------------------
	
	
	//-----------------ProdctDao-----------------------
	private int totalReview;//총 리뷰 수
	private float avgStar;//평균 별점
	//-----------------//ProdctDao-----------------------
	
	
	
	public DTO() {}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	
	
	public int getTotalScrap() {
		return totalScrap;
	}

	public void setTotalScrap(int totalScrap) {
		this.totalScrap = totalScrap;
	}
	
	

	public int getTotalReview() {
		return totalReview;
	}

	public void setTotalReview(int totalReview) {
		this.totalReview = totalReview;
	}

	public float getAvgStar() {
		return avgStar;
	}

	public void setAvgStar(float avgStar) {
		this.avgStar = avgStar;
	}

	@Override
	public String toString() {
		return "DTO [num=" + num + ", totalCnt=" + totalCnt + ", totalScrap=" + totalScrap + ", totalReview="
				+ totalReview + ", avgStar=" + avgStar + "]";
	}


	
	
}
