package com.spring.myweb.freeboard.mapper;

import java.util.List;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.entity.FreeBoard;

public interface IFreeBoardMapper {
	//추상 메서드 작성 
	
	// 글 등록 : select 문 조회 
	void regist(FreeBoard freeBoard);
	
	// 총 게시물 구하기
	int getTotal(Page page);
	 
	// 글 목록
	List<FreeBoard> getList(Page page);
	
	// 상세보기
	FreeBoard getContent(int bno);
	// 수정
	void update(FreeBoard freeBoard);
	
	// 삭제
	void delete(int bno);
	

}
