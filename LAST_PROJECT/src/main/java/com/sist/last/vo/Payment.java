package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Payment extends DTO {

	private String paySeq	; //결제번호
	private String storeSeq ; //상품번호
	private String memberId ; //멤버ID
	private String title	; //상품명
	private String optone	; //옵션1선택명
	private String opttwo	; //옵션2선택명
	private int    quantity	; //수량
	private int    price	; //가격
	private int    shipfee	; //배송비
	private int    status	; //결제상태
	private String regDt	; //등록일
	private String modId	; //수정자
	private String modDt	; //수정일
	
	public Payment() {}

	public Payment(String paySeq, String storeSeq, String memberId, String title, String optone, String opttwo,
			int quantity, int price, int shipfee, int status, String regDt, String modId, String modDt) {
		super();
		this.paySeq = paySeq;
		this.storeSeq = storeSeq;
		this.memberId = memberId;
		this.title = title;
		this.optone = optone;
		this.opttwo = opttwo;
		this.quantity = quantity;
		this.price = price;
		this.shipfee = shipfee;
		this.status = status;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getPaySeq() {
		return paySeq;
	}

	public void setPaySeq(String paySeq) {
		this.paySeq = paySeq;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getShipfee() {
		return shipfee;
	}

	public void setShipfee(int shipfee) {
		this.shipfee = shipfee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
		return "Payment [paySeq=" + paySeq + ", storeSeq=" + storeSeq + ", memberId=" + memberId + ", title=" + title
				+ ", optone=" + optone + ", opttwo=" + opttwo + ", quantity=" + quantity + ", price=" + price
				+ ", shipfee=" + shipfee + ", status=" + status + ", regDt=" + regDt + ", modId=" + modId + ", modDt="
				+ modDt + ", toString()=" + super.toString() + "]";
	}
	
	
}
