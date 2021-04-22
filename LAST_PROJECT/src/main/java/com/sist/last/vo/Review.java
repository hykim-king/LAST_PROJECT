package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Review extends DTO {

	private String reviewSeq;// 리뷰번호
	private String memberId;// 멤버id
	private String reviewFk;// 상품/집들이/q&a fk값
	private String div;// 리뷰 구분(상품:0, 집들이:1, q&a:2)
	private String contents;// 내용
	private String regDt;// 등록일
	private String modId;// 수정자
	private String modDt;// 수정일

	public Review() {

	}

	public Review(String reviewSeq, String memberId, String reviewFk, String div, String contents, String regDt,
			String modId, String modDt) {
		super();
		this.reviewSeq = reviewSeq;
		this.memberId = memberId;
		this.reviewFk = reviewFk;
		this.div = div;
		this.contents = contents;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getReviewSeq() {
		return reviewSeq;
	}

	public void setReviewSeq(String reviewSeq) {
		this.reviewSeq = reviewSeq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getReviewFk() {
		return reviewFk;
	}

	public void setReviewFk(String reviewFk) {
		this.reviewFk = reviewFk;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "Review [reviewSeq=" + reviewSeq + ", memberId=" + memberId + ", reviewFk=" + reviewFk + ", div=" + div
				+ ", contents=" + contents + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + "]";
	}

}
