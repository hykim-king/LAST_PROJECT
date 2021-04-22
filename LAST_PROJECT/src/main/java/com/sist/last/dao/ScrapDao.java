package com.sist.last.dao;

import java.sql.SQLException;
import java.util.List;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.Scrap;

public interface ScrapDao {
	
	/**
	 * 
	 * @param DTO
	 * @return 	 * 
	 * @throws SQLException
	 */
	DTO  doSelectOne(DTO dto) throws  SQLException;
	
	/**
	 * 
	 * @param DTO
	 * @return  * 
	 * @throws SQLException
	 */
	int  doInsert(DTO dto) throws  SQLException;
	
	/**
	 * 
	 * @param DTO
	 * @return 	 * 
	 * @throws SQLException
	 */
	int  doDelete(DTO dto) throws  SQLException;
	
	
	/**
	 * 
	 * @param DTO
	 * @return 	 * 
	 * @throws SQLException
	 */
	List<?>  doRetrieve (DTO dto) throws  SQLException;

}
