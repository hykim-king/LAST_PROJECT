package com.sist.last.vo;

import com.sist.last.cmn.DTO;

public class Member extends DTO {

	private String memberId;	//멤버ID
	private String imgId;		//이미지ID
	private String passwd;		//비밀번호
	private String nickname;	//닉네임
	private String introduce;	//소개
	private int grade;			//등급
	private int div;			//회원구분
	private int scrap;			//스크랩 수
	private int login;			//로그인
	private String regDt;		//등록일
	private String modId;		//수정자
	private String modDt;		//수정일
	
	public Member() {}


	public Member(String memberId, String imgId, String passwd, String nickname, String introduce, int grade, int div,
			int scrap, int login, String regDt, String modId, String modDt) {
		super();
		this.memberId = memberId;
		this.imgId = imgId;
		this.passwd = passwd;
		this.nickname = nickname;
		this.introduce = introduce;
		this.grade = grade;
		this.div = div;
		this.scrap = scrap;
		this.login = login;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getDiv() {
		return div;
	}

	public void setDiv(int div) {
		this.div = div;
	}
	
	public int getScrap() {
		return scrap;
	}

	public void setScrap(int scrap) {
		this.scrap = scrap;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
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
		return "Member [memberId=" + memberId + ", imgId=" + imgId + ", passwd=" + passwd + ", nickname=" + nickname
				+ ", introduce=" + introduce + ", grade=" + grade + ", div=" + div + ", scrap=" + scrap + ", login="
				+ login + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + ", toString()="
				+ super.toString() + "]";
	}
	
}
