package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

	@GetMapping("/order")
	public String coffeeOrder() {
		System.out.println("/coffee/order : Get 요청");
		return "response/coffee-form";
	}
	
	//주문하기 버튼을 누르면 들어오는 요청을 받는 메서드
	@PostMapping("/result")
	public String coffeeresult(String menu, 
								@RequestParam(defaultValue = "3000") int price,
					// 이벤트가 발생하지않으면 input 태그에 value가 안들어가서
					// 아무것도 들어오지 않으면 (= americano) 기본값 3000이다!
								Model model) {
		System.out.println("menu: " + menu );
		System.out.println("price: " + price);
		
		model.addAttribute("menu",menu);
		model.addAttribute("p",price);
		return"response/coffee-result";
	}
}
