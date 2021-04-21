package com.sist.last.service;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.Opt;

public interface OptService {

	/**
	 * 목록조회
	 * @param dto
	 * @return List<Opt>
	 * @throws SQLException
	 */
	List<Opt> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 옵션 수정
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	int doUpdate(DTO dto) throws SQLException;
	
	//예외처리, 자원반납 : jdbcTemplate 내부로 이동
	//query와 param만 처리해주면 됨
	/**
	 * 옵션 삭제
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	int doDelete(DTO dto) throws SQLException;

	/**
	 * 옵션 등록
	 * @param dto
	 * @return int(1:성공, 0:실패)
	 * @throws SQLException
	 */
	int doInsert(DTO dto) throws SQLException;

	/**
	 * 옵션 단건조회
	 * @param DTO
	 * @return DTO
	 * @throws SQLException
	 */
	DTO doSelectOne(DTO dto) throws SQLException;
	
}
