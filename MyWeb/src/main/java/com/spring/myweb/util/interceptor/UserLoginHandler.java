package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//인터셉터로 사용할 클래스는 HandlerInterceptor 인터페이스를 구현합니다.
public class UserLoginHandler implements HandlerInterceptor {
	
	//preHandle은 컨트롤러로 요청이 들어가기전 처리해야 할 로직을 작성.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("나는 preHandle이당!");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	// postHandle 은 컨트롤러를 나갈 때 공통 처리해야 할 내용을 작성.
	// /userLogin 이라는 요청이 컨트롤러에서 마무리 된 후 , viewResolver로 전달이 되기 전
	// 로그인 성공 or 실패 여부에 따라 처리할 로직을 작성할 계획입니다
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(" 로그인 인터셉터가 동작을 합니다. ");
		System.out.println(" 요청 방식:  " + request.getMethod());
		
		if(request.getMethod().equals("POST")) {//요청 방식이 POST라면 -> 로그인 요청이었다면.
			ModelMap map = modelAndView.getModelMap(); // 모델 객체 꺼내기
			String result = (String) map.get("result");
			
			if(result != null) { //아이디가 왔다는거 
				System.out.println("로그인 성공 로직이 동작합니다.");
				//로그인 성공!
				//로그인 성공한 회원에게는 세션데이터를 생성해서 로그인 유지를 하게 해 줌. 
				HttpSession session =  request.getSession();
				session.setAttribute("login", result);
				//request.getContextPath() context-root가 바뀌는거 감지해서 들어옴 지금은myweb
				response.sendRedirect(request.getContextPath() + "/"); //메인페이지로 ㅣ이동
				
			}else {
				//로그인 실패!
				modelAndView.addObject("msg","loginFail");//로그인을 실패해서 들어온사람이란거 
				
				
			}
		}
		
		
	}
	
}
