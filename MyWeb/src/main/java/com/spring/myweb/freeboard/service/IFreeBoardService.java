package com.spring.myweb.freeboard.service;

import java.util.List;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.dto.request.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.request.FreeUpdateRequestDTO;
import com.spring.myweb.freeboard.dto.response.FreeContentResponseDTO;
import com.spring.myweb.freeboard.dto.response.FreeListResponseDTO;

//ctrl + shift + O import 단축키 
public interface IFreeBoardService {

	// 글 등록 : select 문 조회 
	void regist(FreeRegistRequestDTO dto);
	 
	// 글 목록
	List<FreeListResponseDTO> getList(Page page);
	
	//총 게시물
	int getTotal(Page page);
	
	// 상세보기
	FreeContentResponseDTO getContent(int bno);
	// 수정
	void update(FreeUpdateRequestDTO dto);
	
	// 삭제
	void delete(int bno);
	
	



	


	
}
