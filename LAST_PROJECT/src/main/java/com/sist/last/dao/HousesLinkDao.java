package com.sist.last.dao;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.HousesLink;



public interface HousesLinkDao {
	
	 int count(HousesLink link)throws ClassNotFoundException,SQLException;
	
	/**
	 * 수정
	 * @param dto
	 * @return int (1:성공/0:실패)
	 * @throws SQLException
	 */
	int doUpdate(DTO dto) throws SQLException;//--doUpdate
	
	
	
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
	

	
	/**
	 * 집들이 게시물 별 링크조회
	 * @param dto
	 * @return DTO
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;//--doSelectOne
	
	/**
	 * 집들이 게시물 링크 목록조회
	 * @param dto
	 * @return List<?>
	 * @throws SQLException
	 */
	List<HousesLink> doRetrieve(DTO dto) throws SQLException;//--doRetrieve
	
	

}
