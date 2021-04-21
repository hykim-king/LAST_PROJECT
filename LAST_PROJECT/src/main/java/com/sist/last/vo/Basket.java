package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Basket extends DTO {
	
	private String basketSeq; 
	private String storeSeq; 
	private String memberId; 
	private String title; 
	private String optone; 
	private String opttwo; 
	private int quantity; 
	private int shipfee; 
	private int price; 
	private String regDt; 
	private String modId; 
	private String modDt;
	
	public Basket() {}

	public Basket(String basketSeq, String storeSeq, String memberId, String title, String optone, String opttwo,
			int quantity, int shipfee, int price, String regDt, String modId, String modDt) {
		super();
		this.basketSeq = basketSeq;
		this.storeSeq = storeSeq;
		this.memberId = memberId;
		this.title = title;
		this.optone = optone;
		this.opttwo = opttwo;
		this.quantity = quantity;
		this.shipfee = shipfee;
		this.price = price;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getBasketSeq() {
		return basketSeq;
	}

	public void setBasketSeq(String basketSeq) {
		this.basketSeq = basketSeq;
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

	public String getOptone() {
		return optone;
	}

	public void setOptone(String optone) {
		this.optone = optone;
	}

	public String getOpttwo() {
		return opttwo;
	}

	public void setOpttwo(String opttwo) {
		this.opttwo = opttwo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getShipfee() {
		return shipfee;
	}

	public void setShipfee(int shipfee) {
		this.shipfee = shipfee;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
		return "Basket [basketSeq=" + basketSeq + ", storeSeq=" + storeSeq + ", memberId=" + memberId + ", title="
				+ title + ", optone=" + optone + ", opttwo=" + opttwo + ", quantity=" + quantity + ", shipfee="
				+ shipfee + ", price=" + price + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
