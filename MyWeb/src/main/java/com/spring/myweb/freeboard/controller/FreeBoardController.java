package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.FreeContentResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.FreeUpdateRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/freeboard") // 공통 url mapping
@RequiredArgsConstructor //컨트롤러도 서비스에서 주입받을수있음! 

public class FreeBoardController {

	private final IFreeBoardService service;
	
	//목록화면 get 으로 오니까 getmapping으로
	@GetMapping("/freeList")
	public void freeList(Model model) {
		System.out.println("/freeboard/freeList:GET!");
		
		model.addAttribute("boardList", service.getList());
		
	}
	
	//글 쓰기 페이지를 열어주는 메서드
	@GetMapping("/freeRegist")
	public void regist() {	}

	
	//글 등록 처리 
	@PostMapping("/freeRegist")
	//regist 요청들어오면 FreeRegistRequestDTO 받겠다
	public String regist(FreeRegistRequestDTO dto) {
		service.regist(dto);
		return "redirect:/freeboard/freeList";
		
	}

	
	//글 상세보기
	@GetMapping("/content")
	public String getContent(int bno,Model model) {
		//나중에 jsp 로 보내야해서 model 필요함
	 	model.addAttribute("article", service.getContent(bno));
	 	// 서비스가 준거 dto 를  model 에담아서 jsp에 보냄 (forword)
		return "freeboard/freeDetail";
	}
	
	// 글 수정페이지로 이동 요총
	@PostMapping("/modPage")
	//@model ~ 에 aritcle 이라는 이름으로 dto 를담겠다
	// addatri 안해도되ㄱ고 model 따로 안꺼내노됨
	public String modPage(@ModelAttribute("article") FreeUpdateRequestDTO dto) {
		return "freeboard/freeModify";
	}
	
	
	// 글 수정하기
	@PostMapping("/modify")
	public String update(FreeUpdateRequestDTO dto) {
		service.update(dto);
		return "redirect:/freeboard/content?bno=" +dto.getBno();
	}
	
	@PostMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		return "redirect:/freeboard/freeList";
	}
	
	
}
