package com.sist.last.dao;

import java.sql.SQLException;

import com.sist.last.cmn.DTO;

public interface StarDao {
	
	/**
	 * 등록
	 * @param DTO
	 * @return int(1:성공,0:실패)	 * 
	 * @throws SQLException
	 */
	int  doInsert(DTO dto) throws  SQLException;
	
	/**
	 * 삭제
	 * @param DTO
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws  SQLException;
	
	/**
	 * 수정
	 * @param DTO
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int  doUpdate(DTO dto)throws SQLException;

}
