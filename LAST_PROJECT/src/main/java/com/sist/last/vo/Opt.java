package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Opt extends DTO {

	private String optSeq  ; //옵션번호
	private String storeSeq; //상품번호
	private String memberId; //멤버ID
	private String title   ; //옵션명
	private int    price   ; //옵션가격
	private int    div	   ; //옵션구분 1OR2
	private String regDt   ; //등록일
	private String modId   ; //수정자
	private String modDt   ; //수정일
	
	public Opt() {}

	public Opt(String optSeq, String storeSeq, String memberId, String title, int price, int div, String regDt,
			String modId, String modDt) {
		super();
		this.optSeq = optSeq;
		this.storeSeq = storeSeq;
		this.memberId = memberId;
		this.title = title;
		this.price = price;
		this.div = div;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getOptSeq() {
		return optSeq;
	}

	public void setOptSeq(String optSeq) {
		this.optSeq = optSeq;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
		return "Opt [optSeq=" + optSeq + ", storeSeq=" + storeSeq + ", memberId=" + memberId + ", title=" + title
				+ ", price=" + price + ", div=" + div + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt
				+ ", toString()=" + super.toString() + "]";
	}

	
}
