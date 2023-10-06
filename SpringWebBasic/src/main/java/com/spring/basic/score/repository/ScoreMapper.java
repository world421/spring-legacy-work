package com.spring.basic.score.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;

//jdbcTemplate 에서 SELECT 쿼리를 위한 ResultSet 사용을 편하게 하기 위한
// 클래스를 생성. (JdbcTemplate 한테 조회된 내용을 어떻게 가공해야하는지를 알려주는 클래스)
// RowMapper인터페이스를 구현해야합니다.
public class ScoreMapper implements RowMapper<Score>{

	@Override
	public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("mapRow 메서드 호출!");
		System.out.println("rowNum : " + rowNum);
		Score score = new Score(
				rs.getInt("stu_num"),
				rs.getString("stu_name"),
				rs.getInt("kor"),
				rs.getInt("eng"),
				rs.getInt("math"),
				rs.getInt("total"),
				rs.getDouble("average"),
				Grade.valueOf(rs.getString("grade"))
			//어떤식으로 포장할지 알려주기 
				);
				
		return score;
	}

	
}
