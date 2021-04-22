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
import com.sist.last.cmn.Search;
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.Basket;


@Repository
public class BasketDaoImpl extends DTO implements BasketDao {

	final static Logger LOG = LoggerFactory.getLogger(BasketDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.basket"; //com.sist.ehr.member.doDelete
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate; 
	
	//Spring JdbcTemplate:This is the central class in the JDBC core package.It simplifies the use of JDBC and helps to avoid common errors.
	JdbcTemplate jdbcTemplate;
	
	//DataSource dataSource;
	
	RowMapper<Basket> row = new RowMapper<Basket>() {
		@Override
		public Basket mapRow(ResultSet rs, int rowNum) throws SQLException {
			Basket basketVO =  new Basket();
			basketVO.setBasketSeq(rs.getString("basket_seq"));
			basketVO.setStoreSeq(rs.getString("store_seq"));
			basketVO.setMemberId(rs.getString("member_id"));
			basketVO.setTitle(rs.getString("title"));
			basketVO.setOptone(rs.getString("optone"));
			basketVO.setOpttwo(rs.getString("opttwo"));
			basketVO.setQuantity(rs.getInt("quantity"));
			basketVO.setShipfee(rs.getInt("shipfee"));
			basketVO.setPrice(rs.getInt("price"));
			basketVO.setRegDt(rs.getString("reg_dt"));
				
			//추가?
			basketVO.setNum(rs.getInt("rnum"));
			basketVO.setTotalCnt(rs.getInt("total_cnt"));
	
			return basketVO;
		}
	};
	
	public BasketDaoImpl() {}
	
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}


	@Override
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Basket basket = (Basket) dto;
		
		String statement = this.NAMESPACE+".doDelete";
		
//		StringBuffer sb = new StringBuffer(300);
//		sb.append("  DELETE FROM basket    \n");
//		sb.append("  WHERE basket_seq = ?  \n");
//
//		Object[] args = {basket.getBasketSeq()};
//		
		LOG.debug("=======================================");
		LOG.debug("====basket=====" + basket);
		LOG.debug("=======================================");
		
//		flag= jdbcTemplate.update(sb.toString(), args);
		flag = this.sqlSessionTemplate.delete(statement, basket); //이걸로 실행! 
		
		return flag;
	}

	
	@Override
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Basket basket = (Basket) dto;
		String statement = this.NAMESPACE+".doInsert"; //매칭할 구문을 유일하게 찾아갈 수 있게!
		
		//basket.setBasketSeq(StringUtil.getPK(""));
		
//		StringBuffer sb = new StringBuffer(300);
//		
//		sb.append("  INSERT INTO basket (     \n");
//		sb.append("      basket_seq,          \n");
//		sb.append("      store_seq,           \n");
//		sb.append("      member_id,           \n");
//		sb.append("      title,               \n");
//		sb.append("      optone,              \n");
//		sb.append("      opttwo,              \n");
//		sb.append("      quantity,            \n");
//		sb.append("      shipfee,             \n");
//		sb.append("      price,               \n");
//		sb.append("      reg_dt               \n");
//		sb.append("  ) VALUES (               \n");
//		sb.append("      ?,                   \n");
//		sb.append("      ?,                   \n");
//		sb.append("      ?,                   \n");
//		sb.append("      ?,                   \n");
//		sb.append("      ?,                   \n");
//		sb.append("      ?,                   \n");
//		sb.append("      ?,                   \n");
//		sb.append("      ?,                   \n");
//		sb.append("      ?,                   \n");
//		sb.append("      SYSDATE              \n");
//		sb.append("  )                        \n");
//		
//		Object[] args = {
//						  basket.getBasketSeq(),
//						  basket.getStoreSeq(),
//						  basket.getMemberId(),
//						  basket.getTitle(),
//						  basket.getOptone(),
//						  basket.getOpttwo(),
//						  basket.getQuantity(),
//						  basket.getShipfee(),
//						  basket.getPrice()						
//		};
		
		LOG.debug("=======================================");
		LOG.debug("====basket=====" + basket);
		LOG.debug("=======================================");
		
//		flag= jdbcTemplate.update(sb.toString(), args);
		flag = this.sqlSessionTemplate.insert(statement, basket);
		
		return flag;
	}

	
	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		Basket inVO = (Basket) dto;
		Basket outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne";

//		StringBuffer sb = new StringBuffer(300);
//		sb.append("  SELECT                     \n");
//		sb.append("      basket_seq,            \n");
//		sb.append("      store_seq,             \n");
//		sb.append("      member_id,             \n");
//		sb.append("      title,                 \n");
//		sb.append("      optone,                \n");
//		sb.append("      opttwo,                \n");
//		sb.append("      quantity,              \n");
//		sb.append("      shipfee,               \n");
//		sb.append("      price,                 \n");
//		sb.append("      TO_CHAR(reg_dt,'YYYY/MM/DD HH24MISS') reg_dt,                \n");
//		sb.append("      1 rnum,                \n");
//		sb.append("      1 total_cnt            \n");	
//		sb.append("  FROM basket                \n");
//		sb.append("  WHERE basket_seq = ?       \n");
//		
//		Object[] args = {inVO.getBasketSeq()};
//		LOG.debug("====args=====" + args);
//
//		outVO = this.jdbcTemplate.queryForObject(sb.toString(), args, row);
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
		LOG.debug("=======================================");
		LOG.debug("=outVO=" + outVO);
		LOG.debug("=======================================");

		if (outVO == null) {
			throw new EmptyResultDataAccessException("여기 EmptyResultDataAccessException",1);
		}

		return outVO;
	}

	
	@Override
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Basket basket = (Basket) dto;
		
		String statement = this.NAMESPACE + ".doUpdate";

//		StringBuffer sb = new StringBuffer(300);
//		sb.append("  UPDATE basket          \n");
//		sb.append("  SET                    \n");
//		sb.append("      title = ?,          \n");
//		sb.append("      optone = ?,         \n");
//		sb.append("      opttwo = ?,         \n");
//		sb.append("      quantity = ?,       \n");
//		sb.append("      shipfee = ?,        \n");
//		sb.append("      price = ?,          \n");
//		sb.append("      mod_id = ?,         \n");
//		sb.append("      mod_dt = SYSDATE   \n");
//		sb.append("  WHERE basket_seq = ?   \n");
//		
//		Object[] args = {
//				  basket.getTitle(),
//				  basket.getOptone(),
//				  basket.getOpttwo(),
//				  basket.getQuantity(),
//				  basket.getShipfee(),
//				  basket.getPrice(),
//				  basket.getMemberId(),
//				  basket.getBasketSeq()
//		};
		LOG.debug("=======================================");
		LOG.debug("====basket=====" + basket);
		LOG.debug("=======================================");
		
//		flag= jdbcTemplate.update(sb.toString(), args);
		 flag = this.sqlSessionTemplate.insert(statement, basket); //insert넣어도 되고 update넣어도됨 들어가서 보면 어차피 다 insert로 넣게됨.

		return flag;
	}

	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		Search param =  (Search) dto;
		
		String statement =this.NAMESPACE+".doRetrieve";
		
//		StringBuffer sb = new StringBuffer(1000);
//		sb.append("  SELECT A.*,B.*                                                                   \n");                                                       
//		sb.append("  FROM(                                                                            \n");
//		sb.append("      SELECT t2.rnum                                                               \n");
//		sb.append("            ,t2.basket_seq                                                         \n"); 
//		sb.append("            ,t2.store_seq                                                          \n");
//		sb.append("            ,t2.member_id                                                          \n"); 
//		sb.append("            ,t2.title                                                              \n");
//		sb.append("            ,t2.optone                                                             \n");
//		sb.append("            ,t2.opttwo                                                             \n");
//		sb.append("            ,t2.quantity                                                           \n");
//		sb.append("            ,t2.shipfee                                                            \n");
//		sb.append("            ,t2.price                                                              \n");
//		sb.append("            ,DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(t2.reg_dt,'YYYYMMDD')      \n");                     
//		sb.append("            ,TO_CHAR(t2.reg_dt,'HH24:MI')                                          \n");           
//		sb.append("            ,TO_CHAR(t2.reg_dt,'YYYY/MM/DD')) reg_dt                               \n");           	
//		sb.append("      FROM(                                                                        \n");
//		sb.append("          SELECT ROWNUM rnum, t1.*                                                 \n");
//		sb.append("          FROM(                                                                    \n");
//		sb.append("               	SELECT *                                                          \n");
//		sb.append("          		FROM basket                                                       \n");
////		sb.append("          		WHERE member_id = ?                                               \n");
//		sb.append("          		ORDER BY reg_dt ASC                                               \n");
//		sb.append("          )t1                                                                      \n");
//		sb.append("      )t2                                                                          \n");
//		sb.append("      WHERE rnum BETWEEN (? * (?-1)+1) AND (? * (?-1) +?)                          \n");
//		//sb.append("      WHERE rnum BETWEEN 1 AND 10                                                \n");
//		sb.append("  ) A                                                                              \n");
//		sb.append("  CROSS JOIN                                                                       \n");
//		sb.append("  (                                                                                \n");
//		sb.append("      SELECT COUNT(*) total_cnt                                                    \n");
//		sb.append("      FROM basket                                                                  \n");
////		sb.append("      WHERE member_id = ?                                                          \n");
//		sb.append("  ) B                                                                              \n");
//		LOG.debug("====sql=====\n" + sb.toString());
//		//query에 파라메터 set
//		List<Object> listArg = new ArrayList<Object>();
//		
//		if(null != param) {				
////		listArg.add(param.getSearchWord());	
//		
//		//페이징 정보
//		listArg.add(param.getPageSize());			
//		listArg.add(param.getPageNum());
//		listArg.add(param.getPageSize());			
//		listArg.add(param.getPageNum());
//		listArg.add(param.getPageSize());
//		
////		listArg.add(param.getSearchWord());			
//		}
		
		LOG.debug("1=param=====");
		
        List<Basket> list = this.sqlSessionTemplate.selectList(statement, param);

        for(Basket vo : list) {
        	LOG.debug("vo:" + vo);
        }
        
		return list;	
	}

}
