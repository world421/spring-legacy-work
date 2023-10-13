package com.spring.myweb.freeboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.freeboard.controller.FreeBoardController;
import com.spring.myweb.freeboard.dto.response.FreeContentResponseDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration // 어플리케이션 컨텍스트의 웹 버전을 생성하는데 사용하는 아노테이션 
public class FreeBoardControllerTest {

	/*
    - 테스트 환경에서 가상의 DispatcherServlet을 사용하기 위한 객체 자동 주입 
    - WebApplicationContext는 Spring에서 제공되는 servlet들을 사용할 수 있도록
    정보를 저장하는 객체입니다.
    */
	
	@Autowired //@WebAppConfiguration 사용해여 autowired 주입받을수있음
	// WebApplicationContext타입의 객체를 주입받겠다 !
	// 컨테이너에 등록된 모든 빈을 사용할 때
	private WebApplicationContext ctx; 
	
//	@Autowired
//	private FreeBoardController controller; -> 이 컨트롤러 하나만 쓸 때 ~! 
	
	//MockMvc 는 웹 어플리케이션을 서버에 배포하지 않아도 스프링 MVC동작을
	// 구현할 수 있는 가상의 구조를 만들어줍니다.
	// 요청나오는지 
	private MockMvc mockMvc; // 언제셋팅되는징 ,setup 메서드에서set ! 
	
	@BeforeEach //테스트 시작시 다른 메서드 실행 전에 항상 먼저 구동되는 기능을 선언.
	//@AfterEach 
	public void setup() {
			// 테스트할때 마다 한 번 씩 실행됨 ! 
		//가상 구조를 세팅
		// 스프링 컨테이너에 등록된 모든 빈과 의존성 주입까지 로드해서 사용이 가능.
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		//테스트할 컨트롤러를 수동으로 주입. 하나의 컨트롤러 자체만 테스트를 진행할때는 이렇게 씁니다.
		//this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();		
	}
//---------------------------------------------------------------------------
	@Test
	@DisplayName("/freeboard/freeList 요청 처리 과정 테스트")
	// 예외처리안해도됨 가상환경이라
	void testList() throws Exception {
		
		//요청을 수행해라 ~ //mock 모조의,가짜 MVC에서 요청을 만드러주는객체 "/freeboard/freeList" 이요청을 보내볼게 
		ModelAndView mv= mockMvc.perform(MockMvcRequestBuilders.get("/freeboard/freeList"))
				.andReturn()//요청의 결과를 받음 
				.getModelAndView(); ///요청 결과에서 ModelAndView 객체를 얻음.
		//컨트롤러에서 Model 객체에 담은 데이터를 확인 
		System.out.println("Model 내에 저장한 데이터 : " + mv.getModelMap()); 
		//컨트롤러에서 응답 페이지로 지정한 문자열을 확인 
		System.out.println("응답 처리를 위해 사용할 페이지 : " + mv.getViewName()); 
	
	}
	
	@Test
	@DisplayName("게시글 등록 완료 후 목록 재요청이 일어날 것이다.")
	void testInsert() throws Exception {
		String viewName= mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/freeRegist")
	//요청과 함께 넘겨주고 싶은 데이터가 있다 ? 
											.param("title","테스트 새 글 제목")
											.param("content","테스트 새 글 내용")
											.param("writer","user01")
					).andReturn().getModelAndView().getViewName();
		
		assertEquals(viewName, "redirect:/freeboard/freeList");
	}
	
	@Test
	@DisplayName("3번 글 상세보기 요청을 넣으면 컨트롤러는 DB에서 가지고 "
			+ "온 글 객체를 model에 담아 freeDetail.jsp로 이동시킬 것이다.")
	
	void testContent() throws Exception {
		// /freeboard/content -> get 
		// bno, title, writer, content, updateDate == null ? regDate , updateDate(수정됨)
		
		ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders.get("/freeboard/content")
											.param("bno","3")
				).andReturn().getModelAndView();
											// getViewname : 경로가 똑같은지 ! 
		assertEquals("freeboard/freeDetail",mav.getViewName());
		FreeContentResponseDTO dto =  (FreeContentResponseDTO) mav.getModelMap().get("article");
		assertEquals(dto.getBno(), 3);
	}
	
	@Test
    @DisplayName("3번글의 제목과 내용을 수정하는 요청을 post방식으로 전송하면 수정이 진행되고, "
            + "수정된 글의 상세보기 페이지로 응답해야 한다.")
    // /freeboard/modify -> post
	// 작성자는 수정하지 않음 
    void testModify() throws Exception {
		String bno = "5";
		String viewName =  mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/modify")
								.param("bno",bno)
								.param("title","수정수정 글 제목")
								.param("content","테스트 수정수정 글 내용")
				).andReturn().getModelAndView().getViewName();

		assertEquals(viewName, "redirect:/freeboard/content?bno=" + bno);
  
    }
    
    @Test
    @DisplayName("3번 글을 삭제하면 목록 재요청이 발생할 것이다.")
    // /freeboard/delete -> post
    void testDelete() throws Exception {
    	assertEquals("redirect:/freeboard/freeList",
    				mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/delete")
    								.param("bno", "3")
    								).andReturn().getModelAndView().getViewName());
    }
	
}
