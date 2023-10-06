package com.spring.basic.board.repository;

import java.util.List;

import com.spring.basic.board.entity.Board;

public interface IBoardMapper {
	// 얘를 인터페이스 구현체를 xml 파일로 만든다
	
	// 추상메서드 ! 
	//게시글 등록
	void insertArticle(Board board);
	
	//게시글 목록
	List<Board> getArticles();
	
	//게시글 상세
	Board getArticle(int bno);
	
	//게시글 수정
	void updateArticle(Board board);
	
	
	//게시글 삭제
	void deleteArticle(int bno);
}
