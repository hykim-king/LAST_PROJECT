package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.HousesLink;





public interface HousesLinkService {
	
	/**
	 * 목록조회
	 * @param dto
	 * @return List<?>
	 * @throws SQLException
	 */
	List<?> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 단건조회
	 * @param dto
	 * @return DTO
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;//--doSelectOne
	
	/**
	 * 수정
	 * @param dto
	 * @return int (1:성공/0:실패)
	 * @throws SQLException
	 */
	int doUpdate(DTO dto) throws SQLException;
	
	/**
	 * 삭제
	 * @param dto
	 * @return  int (1:성공/0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws  SQLException;//--doDelete
	
	/**
	 * 등록
	 * @param dto
	 * @return  int (1:성공/0:실패)
	 * @throws SQLException
	 */
	int doInsert(DTO dto) throws  SQLException;//--doInsert
	

}//--interface
