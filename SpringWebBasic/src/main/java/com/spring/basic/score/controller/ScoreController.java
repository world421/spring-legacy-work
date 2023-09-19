package com.spring.basic.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor //: final 필드가 존재한다면 그것 만을 초기화 해주는 생성자.
public class ScoreController {

	//필요한 객체의 선언을 final + @requiredArgsConstructor 
	private final ScoreService service;
	
	// 만약에 클래스의 생성자가 단 1개라면
	// 자동으로 @Autowired를 작성해 줌.
	
	// 1번. 성적 등록 화면 띄우기 + 정보 목록 조회
	@GetMapping("/list")
	public String list() {
		return "score/score-list";
	}
	// 2번. 성적 정보 등록 요청 처리
	@PostMapping("/register")
	public String register(ScoreRequestDTO dto) {
		System.out.println("/score/register : POST! - " + dto);	
		//서비스한테 일시켜야지 
		service.insertScore(dto);

		return null;
		
		
		
	}
		
 
}
