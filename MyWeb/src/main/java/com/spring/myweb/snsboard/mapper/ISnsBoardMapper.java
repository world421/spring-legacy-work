package com.spring.myweb.snsboard.mapper;

import java.util.List;
import java.util.Map;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.snsboard.entity.SnsBoard;

public interface ISnsBoardMapper {

	//등록
	void insert (SnsBoard board);
	
	//목록 
	List<SnsBoard> getList(Page page);
	
	//상세
	SnsBoard getDetail(int bno);
	
	//삭제
	void delete(int bno);
	
	// 좋아요 탐색
	int searchLike(Map<String,String> params);
	
	// 좋아요 등록
	void createLike(Map<String,String> params);
	
	// 좋아요 삭제
	void deleteLike(Map<String,String> params);

	// 특정 회원의 좋아요 글 번호
	List<Integer> likeList(String userId); // 객체들의 모음이라 integer로 써줘야함 
}
