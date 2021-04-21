package com.sist.last.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.sist.last.cmn.SearchOrder;
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.Product;

@Repository
public class ProductDaoImpl extends DTO  {
	final static Logger LOG = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.product";//com.sist.ehr.member.doDelete
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Spring JdbcTemplate
	JdbcTemplate jdbcTemplate;
	
	//DataSource dataSource;
	

	
	public ProductDaoImpl() {} //디폴트 생성자
	
	//setter를 통한 주입
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}
	
	
	public List<?> doRetrieve(DTO dto) throws SQLException {

		SearchOrder param = (SearchOrder) dto;
		
		LOG.debug("1=param====="+param);

		String statement = this.NAMESPACE + ".doRetrieve";
		
		LOG.debug("====statement=====" + statement);
		LOG.debug("=======================================");
		
		List<Product> list = sqlSessionTemplate.selectList(statement, param);
		
		for (Product vo : list) {
			LOG.debug(vo.toString());
		}

		return list;
		
		
		
	}
	
	
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Product product = (Product) dto;
		String statement = this.NAMESPACE+".doUpdate";
		
		flag= this.sqlSessionTemplate.insert(statement, product);
		
		return flag;
	}


	
	
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Product product = (Product) dto; //다운캐스팅

		String statement = this.NAMESPACE+".doDelete";
	
		
		
		flag= this.sqlSessionTemplate.delete(statement, product);
		
		return flag;
	}
	


	
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Product product = (Product) dto;
		
		//product.setStoreSeq(StringUtil.getPK(""));
		
		String statement = this.NAMESPACE+".doInsert";
		
		flag= this.sqlSessionTemplate.insert(statement, product);
		
		
		return flag;
	}
	
	
	public DTO doSelectOne(DTO dto) throws SQLException {
		
		Product inVO = (Product) dto;
		Product outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne";
		
		LOG.debug("=======================================");
		LOG.debug("====inVO=====" + inVO);
		LOG.debug("====statement=====" + statement);
		LOG.debug("=======================================");

	
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
		
		
		LOG.debug("=============================");
		LOG.debug("=outVO="+outVO);
		LOG.debug("=============================");	
		

		if (outVO == null) {
			throw new EmptyResultDataAccessException(1);
		}

		
		
		return outVO;
	}
	
	
	

}
