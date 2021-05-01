package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;
import com.sist.last.cmn.Message;
import com.sist.last.vo.Member;

public interface MemberService {
	
	public int doLoginCnt(DTO dto) throws SQLException;
	
	/**
	 * 비밀번호 찾기 메일 전송
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public String sendPasswdEmail(String email) throws SQLException;
	
	/**
	 * 이메일 인증번호 전송
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public String sendAuthEmail(String email) throws SQLException;
	
	/**
	 * nickname check
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int idCheck(DTO dto) throws SQLException;
	
	/**
	 * id check
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int nickCheck(DTO dto) throws SQLException;
	
	/**
	 * id, 비번 check
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public Message loginCheck(DTO dto) throws SQLException;
	
	/**
	 * 단건조회
	 * @param DTO
	 * @return DTO
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;
	
	/**
	 * 수정
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	int doUpdate(DTO dto) throws SQLException;
	
	/**
	 * 삭제
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws SQLException;
	
	/**
	 * 최초 회원가입 시 등급: NEW
	 * Grade가 Null이면 NEW 처리
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	int doInsert(DTO dto) throws SQLException;
	
	//회원 데이터를 모두 조회한다.
	//회원 데이터를 1건 조회 후 등업 대상인지 확인한다.
	// -사용자 레벨은 : BASIC, SILVER, GOLD
	// -사용자가 처음 가입하면 : BASIC
	// -가입 이후 50회 이상 로그인 하면 : SILVER
	// -SILVER 레벨이면서 30번 이상 추천을 받으면 GOLD로 레벨 up
	// -사용자 레벨의 변경 작업은 일정한 주기를 가지고 일괄처리(트랜잭션 관리)
	//등업 대상이면 : 해당 레벨로 등업한다.
	void upgradeGrades(DTO dto) throws SQLException, IllegalAccessException;//--upgradeLevels

	
}
