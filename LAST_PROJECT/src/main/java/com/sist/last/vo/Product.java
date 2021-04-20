package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Product extends DTO {

	private String storeSeq;  	//상품번호
	private String memberId;  	//멤버ID
	private String imgId;  		//이미지ID
	private String title;  		//상품명
	private String contents;  	//내용
	private String company;  	//제조사
	private String category; 	//카테고리
	private String tag;  		//검색태그
	private int price;  		//가격
	private int quantity;  		//수량
	private String refund;  	//배송환불정보
	private String regDt;  		//등록일
	private String modId;  		//수정자
	private String modDt;  		//수정일
	
	public Product() {}

	public Product(String storeSeq, String memberId, String imgId, String title, String contents, String company,
			String category, String tag, int price, int quantity, String refund, String regDt, String modId,
			String modDt) {
		super();
		this.storeSeq = storeSeq;
		this.memberId = memberId;
		this.imgId = imgId;
		this.title = title;
		this.contents = contents;
		this.company = company;
		this.category = category;
		this.tag = tag;
		this.price = price;
		this.quantity = quantity;
		this.refund = refund;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
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
		return "Product [storeSeq=" + storeSeq + ", memberId=" + memberId + ", imgId=" + imgId + ", title=" + title
				+ ", contents=" + contents + ", company=" + company + ", category=" + category + ", tag=" + tag
				+ ", price=" + price + ", quantity=" + quantity + ", refund=" + refund + ", regDt=" + regDt + ", modId="
				+ modId + ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
