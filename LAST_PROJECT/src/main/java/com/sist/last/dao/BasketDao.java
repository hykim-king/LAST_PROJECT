package com.sist.last.dao;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;

public interface BasketDao {
	/**
	 * 삭제
	 * @param dto
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws SQLException;

	/**
	 * 등록 
	 * @param user
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doInsert(DTO dto) throws SQLException;
	
	/**
	 * 단건 조회
	 * @param DTO
	 * @throws DTO
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;
	
	/**
	 * 수정
	 * @param dto
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doUpdate(DTO dto) throws SQLException;
	
	/**
	 * 목록 조회
	 * @param dto
	 * @return List<?>
	 * @throws SQLException
	 */
	List<?> doRetrieve(DTO dto) throws SQLException;
}
