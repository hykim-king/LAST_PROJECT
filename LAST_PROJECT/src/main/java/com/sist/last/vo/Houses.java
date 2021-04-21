package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Houses extends DTO {

	private String housesSeq; /* 집들이번호 */
	private String memberId;  /* 멤버ID */
	private String imgId;     /* 이미지ID */
	private String title;     /* 제목 */
	private String contents;  /* 내용 */
	private String tag;       /* 검색태그 */
	private String regDt;     /* 등록일 */
	private String modId;     /* 수정자 */
	private String modDt;     /* 수정일 */
	
	public Houses() {
		
	}

	public Houses(String housesSeq, String memberId, String imgId, String title, String contents, String tag,
			String regDt, String modId, String modDt) {
		super();
		this.housesSeq = housesSeq;
		this.memberId = memberId;
		this.imgId = imgId;
		this.title = title;
		this.contents = contents;
		this.tag = tag;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getHousesSeq() {
		return housesSeq;
	}

	public void setHousesSeq(String housesSeq) {
		this.housesSeq = housesSeq;
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
		return "Houses [housesSeq=" + housesSeq + ", memberId=" + memberId + ", imgId=" + imgId + ", title=" + title
				+ ", contents=" + contents + ", tag=" + tag + ", regDt=" + regDt + ", modId=" + modId + ", modDt="
				+ modDt + ", toString()=" + super.toString() + "]";
	}
	
}
