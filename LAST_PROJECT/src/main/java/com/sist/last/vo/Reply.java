package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Reply extends DTO {
	
	private String replySeq;   //답글번호
	private String memberId;   //멤버ID
	private String reviewSeq;  //리뷰번호
	private String contents;    //내용
	private String regDt;      //등록일
	private String modId;      //수정자
	private String modDt;      //수정일
	
	public Reply() {}

	public Reply(String replySeq, String memberId, String reviewSeq, String contents, String regDt, String modId,
			String modDt) {
		super();
		this.replySeq = replySeq;
		this.memberId = memberId;
		this.reviewSeq = reviewSeq;
		this.contents = contents;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getReplySeq() {
		return replySeq;
	}

	public void setReplySeq(String replySeq) {
		this.replySeq = replySeq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getReviewSeq() {
		return reviewSeq;
	}

	public void setReviewSeq(String reviewSeq) {
		this.reviewSeq = reviewSeq;
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
		return "Reply [replySeq=" + replySeq + ", memberId=" + memberId + ", reviewSeq=" + reviewSeq + ", contents="
				+ contents + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + ", toString()="
				+ super.toString() + "]";
	}

	
}
