package com.sist.last.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.Grade;
import com.sist.last.vo.Member;

@Repository
public class MemberDaoImpl {
	final static Logger LOG = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.member"; 
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public MemberDaoImpl() {}
	
	/**
	 * 로그인 횟수
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int doLoginCnt(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		String statement = this.NAMESPACE+".doLoginCnt";
		LOG.debug("==================================");
		LOG.debug("=member="+member);
		LOG.debug("=statement="+statement);
		flag = this.sqlSessionTemplate.update(statement, member);
		LOG.debug("=flag="+flag);
		LOG.debug("==================================");
		
		return flag;
	}
	
	/*
	 * 1. id존재 유무
	 * 2. 비번존재 유무
	 * 3. 로그인 + session
	 * 
	 * 예외처리
	 */
	/**
	 * 비밀번호 check
	 * @param dto
	 * @return 
	 * @throws SQLException
	 */
	public int passwordCheck(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		String statement = this.NAMESPACE+".passwordCheck";
		
		LOG.debug("==================================");
		LOG.debug("=member="+member);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		flag = this.sqlSessionTemplate.selectOne(statement, member);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	/**
	 * nickname 존재 check
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int nickCheck(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		String statement = this.NAMESPACE+".nickCheck";
		
		LOG.debug("==================================");
		LOG.debug("=member="+member);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		flag = this.sqlSessionTemplate.selectOne(statement, member);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	/**
	 * id 존재 check
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int idCheck(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		String statement = this.NAMESPACE+".idCheck";
		
		LOG.debug("==================================");
		LOG.debug("=member="+member);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		flag = this.sqlSessionTemplate.selectOne(statement, member);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	/**
	 * 회원 수정
	 * @param dto
	 * @return 수정 성공(1)
	 * @throws SQLException
	 */
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		
		String statement = this.NAMESPACE+".doUpdate";
		LOG.debug("==================================");
		LOG.debug("=member="+member);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		flag = this.sqlSessionTemplate.insert(statement, member);
		
		return flag;
	}
	
	/**
	 * 회원 삭제
	 * @param dto
	 * @return 삭제 성공(1)
	 * @throws SQLException
	 */
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		
		String statement = this.NAMESPACE+".doDelete";
		LOG.debug("==================================");
		LOG.debug("=member="+member);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		flag = this.sqlSessionTemplate.insert(statement, member);
		
		return flag;
	}

	/**
	 * 회원 등록
	 * @param dto
	 * @return 등록 성공(1)
	 * @throws SQLException
	 */
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		
		String statement = this.NAMESPACE+".doInsert";
		LOG.debug("==================================");
		LOG.debug("=member="+member);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		flag = this.sqlSessionTemplate.insert(statement, member);
		
		return flag;
	}

	/**
	 * 단건조회
	 * @param dto
	 * @return DTO
	 * @throws SQLException
	 */
	public DTO doSelectOne(DTO dto) throws SQLException {
		Member inVO = (Member) dto;
		Member outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne";
		LOG.debug("==================================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
		LOG.debug("==================================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("==================================");
		
		if(null == outVO) {
			LOG.debug("==================================");
			LOG.debug("=null outVO="+outVO);
			LOG.debug("==================================");
			throw new EmptyResultDataAccessException(1);
		}
		
		return outVO;
	}

	/**
	 * 다건조회
	 * @param member
	 * @return List<Member>
	 * @throws SQLException
	 */
	public List<Member> getAll(Member member) throws SQLException {
		List<Member> list = null;
		String statement = this.NAMESPACE+".getAll";
		
		LOG.debug("==================================");
		LOG.debug("=param=\n"+member);
		LOG.debug("=statement="+statement);
		LOG.debug("==================================");
		
		list = this.sqlSessionTemplate.selectList(statement, member);
		for(Member vo: list) {
			LOG.debug("vo:"+vo);;
		}
		
		return list;
	}

}
