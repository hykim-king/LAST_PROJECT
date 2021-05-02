package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;

public interface StarService {
	
	/**
	 * 목록조회
	 * 
	 * @param review
	 * @return List<Review>
	 * @throws SQLException
	 */
	public List<?> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 수정
	 * @param DTO
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	int doUpdate(DTO dto) throws SQLException;
	
	/**
	 * 삭제
	 * @param DTO
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws SQLException;

	/**
	 * 등록
	 * @param DTO
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	int doInsert(DTO dto) throws SQLException;

}
