package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.FreeContentResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
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
	
	//글 등록 처리 
	@PostMapping("/freeRegist")
	//regist 요청들어오면 FreeRegistRequestDTO 받겠다
	public String regist(FreeRegistRequestDTO dto) {
		service.regist(dto);
		return "redirect:/freeboard/freeList";
		
	}
	
	@GetMapping("/freecontent")
	public void getContent(FreeContentResponseDTO dto) {
		
	}
	
}
