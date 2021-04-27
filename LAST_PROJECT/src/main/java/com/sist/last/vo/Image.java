package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Image extends DTO {

	private String imgId;// 이미지ID
	private int imgNum;// 이미지 번호
	private String orgName;// 원본이름
	private String saveName;// 저장 이름
	private String savePath;// 저장 경로
	private long imgSize;// 이미지 크기
	private String imgExt;// 확장자

	public Image() {

	}

	public Image(String imgId, int imgNum, String orgName, String saveName, String savePath, long imgSize,
			String imgExt) {
		super();
		this.imgId = imgId;
		this.imgNum = imgNum;
		this.orgName = orgName;
		this.saveName = saveName;
		this.savePath = savePath;
		this.imgSize = imgSize;
		this.imgExt = imgExt;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public int getImgNum() {
		return imgNum;
	}

	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public long getImgSize() {
		return imgSize;
	}

	public void setImgSize(long imgSize) {
		this.imgSize = imgSize;
	}

	public String getImgExt() {
		return imgExt;
	}

	public void setImgExt(String imgExt) {
		this.imgExt = imgExt;
	}

	@Override
	public String toString() {
		return "Image [imgId=" + imgId + ", imgNum=" + imgNum + ", orgName=" + orgName + ", saveName=" + saveName
				+ ", savePath=" + savePath + ", imgSize=" + imgSize + ", imgExt=" + imgExt + "]";
	}

}
