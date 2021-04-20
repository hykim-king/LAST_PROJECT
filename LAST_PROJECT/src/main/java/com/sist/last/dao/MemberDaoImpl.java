package com.sist.last.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sist.last.cmn.DTO;
import com.sist.last.vo.Member;

public class MemberDaoImpl implements MemberDao {
	final static Logger LOG = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	
	RowMapper<Member> row = new RowMapper<Member>() {
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member memberVO = new Member();
			memberVO.setMemberId(rs.getString("member_id"));
			memberVO.setImgId(rs.getString("img_id"));
			memberVO.setPasswd(rs.getString("passwd"));
			memberVO.setNickname(rs.getString("nickname"));
			memberVO.setIntroduce(rs.getString("introduce"));
			memberVO.setGrade(rs.getInt("grade"));
			memberVO.setDiv(rs.getInt("div"));
			memberVO.setRegDt(rs.getString("reg_dt"));
			memberVO.setModId(rs.getString("mod_id"));
			memberVO.setModDt(rs.getString("mod_dt"));
			
//			LOG.debug("rowNum:"+rowNum);
			return memberVO;
		}
	};
	
	
	public MemberDaoImpl() {}
	
	//setter를 통한 주입
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	@Override
	public int doUpdate(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		StringBuffer sb = new StringBuffer(200);
		
		sb.append(" UPDATE member           \n");
		sb.append(" SET                     \n");
		sb.append(" 	passwd = ?,         \n");
		sb.append(" 	nickname = ?,       \n");
		sb.append(" 	introduce = ?,      \n");
		sb.append(" 	grade = ?,          \n");
		sb.append(" 	div = ?,            \n");
		sb.append(" 	reg_dt = SYSDATE,   \n");
		sb.append(" 	mod_id = ?,         \n");
		sb.append(" 	mod_dt = SYSDATE    \n");
		sb.append(" WHERE member_id = ?     \n");
		LOG.debug("==sql==\n"+sb.toString());
		
		Object[] args = {
				member.getPasswd()
				,member.getNickname()
				,member.getIntroduce()
				,member.getGrade()
				,member.getDiv()
				,member.getModId()
				,member.getMemberId()
		};
		
		LOG.debug("==args==\n"+args);
		flag = jdbcTemplate.update(sb.toString(), args);
		
		return flag;
	}

	@Override
	public int doDelete(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		
		StringBuffer sb = new StringBuffer(200);
		sb.append(" DELETE FROM member     		\n");
		sb.append(" WHERE member_id = ?         \n");
		LOG.debug("==sql==\n"+sb.toString());
		
		Object[] args = {member.getMemberId()};
		LOG.debug("=================");
		LOG.debug("==member=="+member);
		LOG.debug("=================");
		
		flag = jdbcTemplate.update(sb.toString(), args);

		return flag;
	}

	@Override
	public int doInsert(DTO dto) throws SQLException {
		int flag = 0;
		Member member = (Member) dto;
		
		StringBuffer sb = new StringBuffer(200);
		sb.append(" INSERT INTO member (   \n");
		sb.append("     member_id,         \n");
		sb.append("     img_id,            \n");
		sb.append("     passwd,            \n");
		sb.append("     nickname,          \n");
		sb.append("     introduce,         \n");
		sb.append("     grade,             \n");
		sb.append("     div,               \n");
		sb.append("     reg_dt,            \n");
		sb.append("     mod_id,            \n");
		sb.append("     mod_dt             \n");
		sb.append(" ) VALUES (             \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     SYSDATE,           \n");
		sb.append("     ?,                 \n");
		sb.append("     SYSDATE            \n");
		sb.append(" )                      \n");
		LOG.debug("==sql==\n"+sb.toString());
		
		Object[] args = {member.getMemberId()
							,member.getImgId()
							,member.getPasswd()
							,member.getNickname()
							,member.getIntroduce()
							,member.getGrade()
							,member.getDiv()
							,member.getModId()
		};
		
		flag = jdbcTemplate.update(sb.toString(), args);
		
		return flag;
	}

	@Override
	public DTO doSelectOne(DTO dto) throws SQLException {
		Member inVO = (Member) dto;
		Member outVO = null;
		
		StringBuffer sb = new StringBuffer(200);
		sb.append(" SELECT                                            \n");
		sb.append("     member_id,                                    \n");
		sb.append("     img_id,                                       \n");
		sb.append("     passwd,                                       \n");
		sb.append("     nickname,                                     \n");
		sb.append("     introduce,                                    \n");
		sb.append("     grade,                                        \n");
		sb.append("     div,                                          \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24MISS') reg_dt, \n");
		sb.append("     mod_id,                                       \n");
		sb.append("     TO_CHAR(mod_dt,'YYYY/MM/DD HH24MISS') mod_dt  \n");
		sb.append(" FROM                                              \n");
		sb.append(" 	member                                        \n");
		sb.append(" WHERE	member_id = ?                             \n");
		LOG.debug("==sql==\n"+sb.toString());
		LOG.debug("==param=="+inVO.getMemberId());
		
		Object[] args = {inVO.getMemberId()};
		LOG.debug("==args==\n"+args);
		
		outVO = this.jdbcTemplate.queryForObject(sb.toString(), args, row);
		LOG.debug("==================================");
		LOG.debug("==outVO=="+outVO);
		LOG.debug("==================================");
		
		if(null == outVO) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return outVO;
	}

}
