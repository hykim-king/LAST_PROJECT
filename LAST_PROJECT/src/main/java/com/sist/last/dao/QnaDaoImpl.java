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


import com.sist.last.cmn.DTO;
import com.sist.last.cmn.Search;
import com.sist.last.cmn.StringUtil;
import com.sist.last.vo.Basket;
import com.sist.last.vo.Qna;

public class QnaDaoImpl implements QnaDao {

	final static Logger LOG = LoggerFactory.getLogger(QnaDaoImpl.class);
	
	final String NAMESPACE = "com.sist.last.qna";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//Spring
	JdbcTemplate jdbcTemplate;
	
	DataSource dataSource;

	RowMapper<Qna> row =  new RowMapper<Qna>() {
		public Qna mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Qna qnaVO = new Qna();
			qnaVO.setQnaSeq(rs.getString("qna_seq"));
			qnaVO.setMemberId(rs.getString("member_id"));
			qnaVO.setImgId(rs.getString("img_id"));
			qnaVO.setTitle(rs.getString("title"));
			qnaVO.setContents(rs.getString("contents"));
			qnaVO.setTag(rs.getString("tag"));
			qnaVO.setRegDt(rs.getString("reg_dt"));
//			qnaVO.setModId(rs.getString("mod_id"));
//			qnaVO.setModDt(rs.getString("mod_dt"));
			
			qnaVO.setNum(rs.getInt("rnum"));
			qnaVO.setTotalCnt(rs.getInt("total_cnt"));
			
			return qnaVO;
			
		}
	};
	
	public QnaDaoImpl() {}
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	
	@Override
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Qna qna =  (Qna) dto;
		String statement = this.NAMESPACE+".doDelete";
		
//		StringBuffer sb = new StringBuffer(300);
//		sb.append("  DELETE FROM qna     \n");
//		sb.append("  WHERE qna_seq = ?   \n");
//
//		LOG.debug("====sb.toString()=====" + sb.toString());
//		
//		Object[] args = {qna.getQnaSeq()};
		LOG.debug("=======================================");
		LOG.debug("====qna=====" + qna);
		LOG.debug("=======================================");
		
//		flag= jdbcTemplate.update(sb.toString(), args);
		flag=sqlSessionTemplate.delete(statement, qna);
		
		return flag;
	}

	
	/**
	 * 등록
	 * @param qna
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Override
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Qna qna =  (Qna) dto;
		String statement = this.NAMESPACE+".doInsert";
		
		
		//qna.setQnaSeq(StringUtil.getPK(""));
		
//		StringBuffer sb = new StringBuffer(300);
//		sb.append("  INSERT INTO qna (    \n");
//		sb.append("      qna_seq,         \n");
//		sb.append("      member_id,       \n");
//		sb.append("      img_id,          \n");
//		sb.append("      title,           \n");
//		sb.append("      contents,        \n");
//		sb.append("      tag,             \n");
//		sb.append("      reg_dt           \n");
//		sb.append("  ) VALUES (           \n");
//		sb.append("      ?,               \n");
//		sb.append("      ?,               \n");
//		sb.append("      ?,               \n");
//		sb.append("      ?,               \n");
//		sb.append("      ?,               \n");
//		sb.append("      ?,               \n");
//		sb.append("      SYSDATE          \n");
//		sb.append("  )                    \n");
//		LOG.debug("====sql=====\n" + sb.toString());
//		Object[] args = {
//							qna.getQnaSeq(),
//							qna.getMemberId(),
//							qna.getImgId(),
//							qna.getTitle(),
//							qna.getContents(),
//							qna.getTag()
//		};
		
		LOG.debug("=======================================");
		LOG.debug("====qna=====" + qna);
		LOG.debug("=======================================");
		
//		flag= jdbcTemplate.update(sb.toString(), args);
		flag=sqlSessionTemplate.insert(statement, qna);
		
		return flag;
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		Qna inVO = (Qna) dto;
		Qna outVO = null;
		
		String statement = this.NAMESPACE+".doSelectOne";
		
//		StringBuffer sb = new StringBuffer(200);
//		sb.append("   SELECT qna_seq,          \n");
//		sb.append("          member_id,        \n");
//		sb.append("          img_id,           \n");
//		sb.append("          title,            \n");
//		sb.append("          contents,         \n");
//		sb.append("          tag,              \n");
//		sb.append("          reg_dt,           \n");
//		sb.append("          1 rnum,           \n");
//		sb.append("          1 total_cnt       \n");		
//		sb.append("   FROM qna                 \n");
//		sb.append("   WHERE qna_seq=?          \n");
//		
//		Object[] args = {inVO.getQnaSeq()};
//		LOG.debug("====args=====" + args);
//		
//		outVO = this.jdbcTemplate.queryForObject(sb.toString(), args, row);
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
			
		LOG.debug("=======================================");
		LOG.debug("=outVO=" + outVO);
		LOG.debug("=======================================");

		if (outVO == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return outVO;
	}

	@Override
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Qna qna =  (Qna) dto;
		
		String statement = this.NAMESPACE + ".doUpdate";
		
//		StringBuffer sb = new StringBuffer(300);
//		
//		sb.append("  UPDATE qna           \n");  
//		sb.append("  SET                  \n");
//		sb.append("  img_id = ?,          \n");
//		sb.append("  title = ?,           \n");
//		sb.append("  contents = ?,        \n");
//		sb.append("  tag = ?,             \n");
//		sb.append("  mod_id = ?,          \n");
//		sb.append("  mod_dt = SYSDATE     \n");
//		sb.append("  WHERE  qna_seq = ?   \n");
//		
//		LOG.debug("====sql=====\n" + sb.toString());
//		
//		Object[] args = {
//				qna.getImgId(),
//				qna.getTitle(),
//				qna.getContents(),
//				qna.getTag(),
//				qna.getModId(),
//				qna.getQnaSeq()
//		};
		LOG.debug("=======================================");
		LOG.debug("====qna=====" + qna);
		LOG.debug("=======================================");
//		flag= jdbcTemplate.update(sb.toString(), args);
		flag = this.sqlSessionTemplate.insert(statement, qna); //insert넣어도 되고 update넣어도됨 들어가서 보면 어차피 다 insert로 넣게됨.

			
		return flag;
	}

	@Override
	public List<?> doRetrieve(DTO dto) throws SQLException {
		
		Search param =  (Search) dto;
		
		String statement = this.NAMESPACE+".doRetrieve";
		
//		StringBuffer sbWhere = new StringBuffer(100);
//		//검색조건: 제목(10), 태그(20), 제목+태그(30)
//		if(null != param && !"".equals(param.getSearchDiv())) { //검색조건이 있을때 -> 쿼리추가
//			if("10".equals(param.getSearchDiv())) {
//				sbWhere.append(" WHERE title LIKE '%'|| ? ||'%' \n");
//			}else if("20".equals(param.getSearchDiv())) {
//				sbWhere.append(" WHERE tag LIKE '%'|| ? ||'%' \n");
//			}else if("30".equals(param.getSearchDiv())) {
//				sbWhere.append(" WHERE title LIKE '%'|| ? ||'%'  \n");//질문
//				sbWhere.append("	   OR tag Like '%'|| ? ||'%' \n");
//			}
//		}
//		LOG.debug("====sbWhere=====\n" + sbWhere.toString());
//		
//		
//		StringBuffer sb = new StringBuffer(1000);
//		sb.append("  SELECT A.*,B.*                                                                 \n");      
//		sb.append("  FROM(                                                                          \n");
//		sb.append("      SELECT t2.rnum                                                             \n");
//		sb.append("            ,t2.qna_seq                                                          \n");
//		sb.append("            ,t2.member_id                                                        \n");
//		sb.append("            ,t2.img_id                                                           \n");
//		sb.append("            ,t2.title                                                            \n");
//		sb.append("            ,t2.contents                                                         \n");
//		sb.append("            ,t2.tag                                                              \n");
//		sb.append("            ,DECODE(TO_CHAR(SYSDATE,'YYYYMMDD'),TO_CHAR(t2.reg_dt,'YYYYMMDD')    \n");                       
//		sb.append("            ,TO_CHAR(t2.reg_dt,'HH24:MI')                                        \n");             
//		sb.append("            ,TO_CHAR(t2.reg_dt,'YYYY/MM/DD')) reg_dt                             \n");             	
//		sb.append("      FROM(                                                                      \n");
//		sb.append("          SELECT rownum rnum,t1.*                                                \n");
//		sb.append("          FROM(                                                                  \n");
//		sb.append("          SELECT *                                                               \n");
//		sb.append("          FROM qna                                                               \n");
//		//sb.append("                 --WHERE 조건                                                                           \n");
//		//------------------------------------------------------------------
//		//검색
//		//------------------------------------------------------------------		
//		sb.append(sbWhere.toString());
//		sb.append("          ORDER BY reg_dt ASC                                                    \n");
//		sb.append("          )t1                                                                    \n");
//		sb.append("      )t2                                                                        \n");
//		sb.append("      WHERE rnum BETWEEN (? * (?-1)+1) AND (? * (?-1) +?)                      \n");
//		//sb.append("      WHERE rnum BETWEEN 1 AND 10                                                \n");
//		sb.append("  ) A                                                                            \n");
//		sb.append("  CROSS JOIN                                                                     \n");
//		sb.append("  (                                                                              \n");
//		sb.append("      SELECT COUNT(*) total_cnt                                                  \n");
//		sb.append("      FROM qna                                                                   \n");
//		//sb.append("                 --WHERE 조건                                                                           \n");
//		//------------------------------------------------------------------
//		//검색
//		//------------------------------------------------------------------		
//		sb.append(sbWhere.toString());
//		sb.append("  ) B                                                                            \n");
//		
//		LOG.debug("====sql=====\n" + sb.toString());
//		LOG.debug("====param=====\n" + param);
//		
//		//query에 파라메터 set
//		List<Object> listArg = new ArrayList<Object>();
//		//검색조건이 있으면 6개
//		if(null != param && !"".equals(param.getSearchDiv())) {
//			//검색
//			listArg.add(param.getSearchWord());
//			if(param.getSearchDiv().equals("30")) {
//				listArg.add(param.getSearchWord());
//			}
//			//페이징 정보
//			listArg.add(param.getPageSize());			
//			listArg.add(param.getPageNum());
//			listArg.add(param.getPageSize());			
//			listArg.add(param.getPageNum());
//			listArg.add(param.getPageSize());
//			//검색
//			listArg.add(param.getSearchWord());
//			if(param.getSearchDiv().equals("30")) {
//				listArg.add(param.getSearchWord());
//			}
//		//검색 조건이 없으면 5개
//		}else {
//			
//			//페이징 정보
//			listArg.add(param.getPageSize());			
//			listArg.add(param.getPageNum());
//			listArg.add(param.getPageSize());			
//			listArg.add(param.getPageNum());
//			listArg.add(param.getPageSize());
//		}
//		
		LOG.debug("1=param=====");
		
//		List<Qna> list = jdbcTemplate.query(sb.toString(), listArg.toArray(), row);
		List<Qna> list = this.sqlSessionTemplate.selectList(statement, param);

		for(Qna vo : list) {
			LOG.debug(vo.toString());
		}
		
		return list;
	}

	
	
}
