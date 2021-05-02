package com.sist.last.dao;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.Basket;


@Repository
public class BasketDaoImpl extends DTO {

	final static Logger LOG = LoggerFactory.getLogger(BasketDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.basket"; //com.sist.ehr.member.doDelete
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate; 
	
	
	public BasketDaoImpl() {}
	
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Basket basket = (Basket) dto;
		
		String statement = this.NAMESPACE+".doDelete";
	
		LOG.debug("=======================================");
		LOG.debug("====basket=====" + basket);
		LOG.debug("=======================================");
		
		flag = this.sqlSessionTemplate.delete(statement, basket); //이걸로 실행! 
		
		return flag;
	}

	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Basket basket = (Basket) dto;
		String statement = this.NAMESPACE+".doInsert"; //매칭할 구문을 유일하게 찾아갈 수 있게!
		
		LOG.debug("=======================================");
		LOG.debug("====basket=====" + basket);
		LOG.debug("=======================================");
		
		flag = this.sqlSessionTemplate.insert(statement, basket);
		
		return flag;
	}

	public DTO doSelectOne(DTO dto) throws SQLException {
		Basket inVO = (Basket) dto;
		Basket outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne";

		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
		LOG.debug("=======================================");
		LOG.debug("=outVO=" + outVO);
		LOG.debug("=======================================");

		if (outVO == null) {
			throw new EmptyResultDataAccessException("여기 EmptyResultDataAccessException",1);
		}

		return outVO;
	}

	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Basket basket = (Basket) dto;
		
		String statement = this.NAMESPACE + ".doUpdate";

		LOG.debug("=======================================");
		LOG.debug("====basket=====" + basket);
		LOG.debug("=======================================");
		
		flag = this.sqlSessionTemplate.insert(statement, basket); //insert넣어도 되고 update넣어도됨 들어가서 보면 어차피 다 insert로 넣게됨.

		return flag;
	}

	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		Basket basket = (Basket) dto;
		
		String statement =this.NAMESPACE+".doRetrieve";
		
		LOG.debug("=======================================");
		LOG.debug("====param=====" + basket);
		LOG.debug("=======================================");
		
        List<Basket> list = this.sqlSessionTemplate.selectList(statement, basket);

        for(Basket vo : list) {
        	LOG.debug("vo:" + vo);
        }
        
		return list;	
	}

	public int basketCheck(DTO dto) throws SQLException {
		int flag = 0;
		Basket basket = (Basket) dto;
		
		String statement = this.NAMESPACE + ".basketCheck";

		LOG.debug("=======================================");
		LOG.debug("====basket=====" + basket);
		LOG.debug("=======================================");
		
		flag = this.sqlSessionTemplate.selectOne(statement, basket);

		return flag;
	}
	
}
