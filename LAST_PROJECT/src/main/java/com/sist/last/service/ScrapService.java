package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.Scrap;

public interface ScrapService {
	
	/**
	 * 단건조회
	 * @param DTO
	 * @return DTO
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;
	
	/**
	 * 목록조회
	 * @param DTO
	 * @return List<?>
	 * @throws SQLException
	 */
	List<Scrap> doRetrieve(DTO dto) throws SQLException;
	
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
