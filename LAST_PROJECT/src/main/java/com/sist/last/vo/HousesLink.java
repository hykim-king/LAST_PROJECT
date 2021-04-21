package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class HousesLink extends DTO{
	
	private String linkSeq;   //링크번호	
	private String housesSeq; //집들이번호
	private String memberId;  //멤버ID	
	private String link	;      //상품주소	
	private int div;           //링크 구분	
	private String regDt;     //등록일	
	private String modId;     //수정자	
	private String modDt;     //수정일	
	
	public HousesLink() { 
		
	}

	public HousesLink(String linkSeq, String housesSeq, String memberId, String link, int div, String regDt,
			String modId, String modDt) {
		super();
		this.linkSeq = linkSeq;
		this.housesSeq = housesSeq;
		this.memberId = memberId;
		this.link = link;
		this.div = div;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getLinkSeq() {
		return linkSeq;
	}

	public void setLinkSeq(String linkSeq) {
		this.linkSeq = linkSeq;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getDiv() {
		return div;
	}

	public void setDiv(int div) {
		this.div = div;
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
		return "HousesLink [linkSeq=" + linkSeq + ", housesSeq=" + housesSeq + ", memberId=" + memberId + ", link="
				+ link + ", div=" + div + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
	
}//--class
