package com.sist.last.dao;

import java.sql.SQLException;

import com.sist.last.cmn.DTO;

public interface MemberDao {

	
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
	 * @return
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws SQLException;

	/**
	 * 등록
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	int doInsert(DTO dto) throws SQLException;

	/**
	 * 단건조회
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;
}
