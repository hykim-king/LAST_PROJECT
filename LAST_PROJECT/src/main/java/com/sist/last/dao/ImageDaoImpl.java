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
import com.sist.last.vo.Image;

@Repository
public class ImageDaoImpl {

	final static Logger LOG = LoggerFactory.getLogger(ImageDaoImpl.class);

	final String NAMESPACE = "com.sist.last.image";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	// spring이 제공하는 jdbcTemplate
	JdbcTemplate jdbcTemplate;

	DataSource dataSource;

	RowMapper<Image> row = new RowMapper<Image>() {

		@Override
		public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
			Image imageVO = new Image();

			imageVO.setImgId(rs.getString("img_id"));
			imageVO.setImgNum(rs.getInt("img_num"));
			imageVO.setOrgName(rs.getString("org_name"));
			imageVO.setSaveName(rs.getString("save_name"));
			imageVO.setSavePath(rs.getString("save_path"));
			imageVO.setImgSize(rs.getString("img_size"));
			imageVO.setImgExt(rs.getString("img_ext"));

			return imageVO;
		}

	};

	public ImageDaoImpl() {

	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		Image param = (Image) dto;
		
		//mybatis sql: NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doRetrieve";
		
		LOG.debug("========================");
		LOG.debug("param: " + param);
		LOG.debug("statement: " + statement);
		LOG.debug("========================");
					
		List<Image> list = sqlSessionTemplate.selectList(statement, param);
		
		for (Image vo : list) {
			LOG.debug(vo.toString());
		}
		
		return list;
	}

	public DTO doSelectOne(DTO dto) throws SQLException {
		Image inVO = (Image) dto;
		Image outVO = null;
		
		//mybatis sql: NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doSelectOne";
		
		LOG.debug("========================");
		LOG.debug("image: " + inVO);
		LOG.debug("statement: " + statement);
		LOG.debug("========================");

		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
		LOG.debug("========================");
		LOG.debug("outVO: " + outVO);
		LOG.debug("========================");
		
		// data가 없을 경우 예외 발생
		if (null == outVO) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return outVO;
		
	}

	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Image image = (Image) dto;
		
		//mybatis sql: NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doUpdate";
		
		LOG.debug("========================");
		LOG.debug("image: " + image);
		LOG.debug("statement: " + statement);
		LOG.debug("========================");
		
		flag = this.sqlSessionTemplate.insert(statement, image);
		
		return flag;
		
	}

	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Image image = (Image) dto;

		//mybatis sql: NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doDelete";
		
		LOG.debug("========================");
		LOG.debug("image: " + image);
		LOG.debug("statement: " + statement);
		LOG.debug("========================");
		
		flag = this.sqlSessionTemplate.insert(statement, image);

		return flag;

	}

	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Image image = (Image) dto;

		//mybatis sql: NAMESPACE+.+id;
		String statement = this.NAMESPACE+".doInsert";
		
		LOG.debug("========================");
		LOG.debug("image: " + image);
		LOG.debug("statement: " + statement);
		LOG.debug("========================");
		
		flag = this.sqlSessionTemplate.insert(statement, image);

		return flag;
		
	}

}
