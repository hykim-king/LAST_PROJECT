package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;



public interface ProductService {
	/**
	 * 상품 단건 조회
	 * @param DTO
	 * @throws DTO
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;
	
	
	/**
	 * 상품 목록 조회
	 * @param dto
	 * @return List<?>
	 * @throws SQLException
	 */
	List<?> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 상품 수정
	 * @param dto
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doUpdate(DTO dto) throws SQLException;
	
	/**
	 * 상품 삭제
	 * @param dto
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws SQLException;
	
	/**
	 * 상품 등록
	 * @param dto
	 * @return int(1:성공,0:실패)
	 * @throws SQLException
	 */
	int doInsert(DTO dto) throws SQLException;
}
