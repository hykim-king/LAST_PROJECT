package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Scrap extends DTO {
	
	private String scrapSeq;//스크랩번호
	private String housesSeq;//집들이번호
	private String memberId;//멤버ID
	private String regDt;//등록일
	private String modId;//수정자
	private String modDt;//수정일
	
	public Scrap() {}
	
	public Scrap(String scrapSeq, String housesSeq, String memberId, String regDt, String modId, String modDt) {
		super();
		this.scrapSeq = scrapSeq;
		this.housesSeq = housesSeq;
		this.memberId = memberId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getScrapSeq() {
		return scrapSeq;
	}

	public void setScrapSeq(String scrapSeq) {
		this.scrapSeq = scrapSeq;
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
		return "Scrap [scrapSeq=" + scrapSeq + ", housesSeq=" + housesSeq + ", memberId=" + memberId + ", regDt="
				+ regDt + ", modId=" + modId + ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}

	
}
