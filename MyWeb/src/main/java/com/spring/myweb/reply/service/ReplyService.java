package com.spring.myweb.reply.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.reply.dto.ReplyListResponseDTO;
import com.spring.myweb.reply.dto.ReplyRegistDTO;
import com.spring.myweb.reply.entity.Reply;
import com.spring.myweb.reply.mapper.IReplyMapper;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class ReplyService implements IReplyService {
	// 	인터페이스로 구현 
	private final IReplyMapper mapper; //매퍼에 주입해줄게없다 ~ xml 파일만들기전
	private final BCryptPasswordEncoder encoder;
	
	@Override
	public void replyRegist(ReplyRegistDTO dto) {
		dto.setReplyPw(encoder.encode(dto.getReplyPw()));
		mapper.replyRegist(dto.toEntity(dto));

	}

	@Override
	public List<ReplyListResponseDTO> getList(int bno, int pageNum) {
		 Page page = Page.builder()
				 .pageNo(pageNum)
				 .amount(5)
				 .build();
		// page.setPageNo(pageNum); // 화면에서 전달된 페이지 번호
		 //page.setAmount(5); //댓글은 한 화면에 5개씩
		 
		//List<Reply> list = mapper.getList(map); // 맵퍼에게 글번호를 부르면서 bno 와 page 객체 전달 ~ 
		 Map<String, Object> map = new HashMap<>();
		 map.put("paging", page);
		 map.put("boardNo", bno);
		 
		//List<Reply> list = mapper.getList(map); // 맵퍼에게 글번호를 부르면서 bno 와 page 객체 전달 ~ 
		 List<ReplyListResponseDTO> dtoList = new ArrayList<>();
		 for(Reply reply : mapper.getList(map)) {
			 dtoList.add(new ReplyListResponseDTO(reply));
		 }
		return dtoList;
	}

	@Override
	public int getTotal(int bno) {
		return mapper.getTotal(bno);
		 	
	}

	@Override
	public String pwCheck(int rno) {
	
		return null;
	}

	@Override
	public void update(Reply reply) {
	

	}

	@Override
	public void delete(int rno) {
		// TODO Auto-generated method stub

	}

}
