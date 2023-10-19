package com.spring.myweb.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myweb.reply.dto.ReplyListResponseDTO;
import com.spring.myweb.reply.dto.ReplyRegistDTO;
import com.spring.myweb.reply.service.IReplyService;

import lombok.RequiredArgsConstructor;

@RestController //controller 대신 @RestController 붙이면 responsebody 안붙여도댐 ! 비동기 주사용 일때
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
	
	private final IReplyService service;
	
	//댓글 등록 요청
	@PostMapping() // 생략해도됨 
	 // 제이슨 객체를 dto 로 변경해줌 

	public String replyRegist(@RequestBody ReplyRegistDTO dto) {
		System.out.println("댓글 등록 요청이 들어옴!" + dto);
		service.replyRegist(dto);
		
		return "regSuccess";
	}
	
	//목록요청 
	@GetMapping("/list/{bno}/{pageNum}")
	public Map<String, Object> getList(@PathVariable int bno, @PathVariable int pageNum) {
		
		/*
		 1. 화면단에서 getList 메서드가 글 번호, 페이지 번호를 url을 통해 전달합니다.
		 2. Mapper 인터페이스에게 복수의 값을 전달하기 위해 Map을 쓸지, @Param 을 쓸 지 결정.
		 3. ReplyMapper.xml 에 sql 문을 페이징 쿼리로 작성. ! 
		 4. 클라이언트 측으로 DB에서 조회한 댓글 목록을 보낼 때,
		 	페이징을 위한 댓글의 총 개수도 함께 보내줘야 합니다.
		 	근데 우리는 return을 한개만 쓸 수 있으니까, 복수 개의 값을 리턴하기 위해 
		 	리턴 타입을 Map으로 줄 지, 객체를 디자인해서 줄지를 결정합니다.
		 	(댓글 목록 리스트와 전체 댓글 개수를 함께 전달할 예정)
		 	-> 일회성으로 쓸거니까 Map으로 클라이언트에게 전달.  
		 */
		
		System.out.println("/list/" + bno + "/" +pageNum );
	 	List<ReplyListResponseDTO> list = service.getList(bno, pageNum); //댓글 목록
		int total = service.getTotal(bno);// 전체 게시글에 달려있는 댓글의 총 개수
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
		
	}
	

}
