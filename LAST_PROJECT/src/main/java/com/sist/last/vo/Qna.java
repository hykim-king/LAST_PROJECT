package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Qna extends DTO {
	
	private String qnaSeq;
	private String memberId;
	private String imgId;
	private String title;
	private String contents;
	private String tag; 
	private String regDt; 
	private String modId; 
	private String modDt;
	
	public Qna () {}

	public Qna(String qnaSeq, String memberId, String imgId, String title, String contents, String tag, String regDt,
			String modId, String modDt) {
		super();
		this.qnaSeq = qnaSeq;
		this.memberId = memberId;
		this.imgId = imgId;
		this.title = title;
		this.contents = contents;
		this.tag = tag;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getQnaSeq() {
		return qnaSeq;
	}

	public void setQnaSeq(String qnaSeq) {
		this.qnaSeq = qnaSeq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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
		return "Qna [qnaSeq=" + qnaSeq + ", memberId=" + memberId + ", imgId=" + imgId + ", title=" + title
				+ ", contents=" + contents + ", tag=" + tag + ", regDt=" + regDt + ", modId=" + modId + ", modDt="
				+ modDt + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
