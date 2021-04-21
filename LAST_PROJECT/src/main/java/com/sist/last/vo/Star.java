package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Star extends DTO {
	
	private String starSeq; //별점번호
	private String storeSeq;//상품번호
	private String memberId;//멤버ID
	private int starScore;//별점
	
	public Star() {}

	public Star(String starSeq, String storeSeq, String memberId, int starScore) {
		super();
		this.starSeq = starSeq;
		this.storeSeq = storeSeq;
		this.memberId = memberId;
		this.starScore = starScore;
	}

	public String getStarSeq() {
		return starSeq;
	}

	public void setStarSeq(String starSeq) {
		this.starSeq = starSeq;
	}

	public String getStoreSeq() {
		return storeSeq;
	}

	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getStarScore() {
		return starScore;
	}

	public void setStarScore(int starScore) {
		this.starScore = starScore;
	}

	@Override
	public String toString() {
		return "Star [starSeq=" + starSeq + ", storeSeq=" + storeSeq + ", memberId=" + memberId + ", starScore="
				+ starScore + ", toString()=" + super.toString() + "]";
	}

	
	
}
	

