package com.spring.myweb.freeboard;

// import static 문법 (클래스이름 안붙이고 사용 가능함!!)
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

@ExtendWith(SpringExtension.class)// 테스트 환경을 만들어주는 Junit5 객체 로딩
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class FreeBoardMapperTest {

	@Autowired 
	//객체를 주입받아야 mapper의 기능 사용 가능 
	//테스트 환경에서는 생성자가 생성이 안됨
	private IFreeBoardMapper mapper;
	
	// 단위테스트(unit test) - 가장 작은 단위의 테스트(기능별 테스트 - > 메서드별 테스트)
	// 테스트 시나리오 
	// - 단언(Assertion) 기법 ! ㅁassertoin 클래스 사용하면 테스트 성공/실패 
	
	@Test
	@DisplayName("Mapper 계층의 regist를 호출하면서"//선택사항으로 테스트 목표
			 	+"FreeBoard 를 전달하면 데이터가 INSERT 될것이다.")
	void registTest() {
		
		//given- when-then 패턴을 따릅니다.(권장사항)
		//테스트에 필요한 값을 주고 - 가정 - 결과
		//given : 테스트를 위해 주어질 데이터 세팅(parameter) --given 지금은 생략 
		for(int i=1 ; i<=300; i++) {
			//when : 테스트 실제 상황 세팅 
			mapper.regist(FreeBoard.builder() //mapper.regist 한다는건 db연동한다는뜻
					.title("페이징 테스트 제목"+ i)
					.writer("page1234")
					.content("테스트 내용입니다."+i )
					.build()); 
			
		}
		
//		mapper.regist(FreeBoard.builder() 
//				.title("테스트11")
//				.writer("kim1234")
//				.content("테스트 내용입니다.")
//				.build()); 
		
		//then: 테스트 결과를 확인.
		
	}
	@Test
	@DisplayName("조회시 전체 글 목록이 올 것이고 , 조회된 글의 개수는 10개 일 것이다. ")
	void getListTest() {
		
		List<FreeBoard> list = mapper.getList(Page.builder()
																									.build());
		
		
		for(FreeBoard board :list) {
			System.out.println(board);
		}
		System.out.println("조회된 글의 개수 : " + list.size());
		
		//then 
		//assertions 는 static 메서드라 객체 생성 필요 x 
		assertEquals(list.size(), 10); // assertEquals 는 size와 준 값을 비교한다 
		
	}
	
	@Test
	@DisplayName("글 번호가31번인 글을 조회하면 글쓴이는 kim 1234일 것이고, 글 제목은 '테스트11'일것이다.")
	
	void getContentTest() {
		//given
		int bno = 31;
		
		//when 
		FreeBoard board = mapper.getContent(bno);
		
		//then
		assertEquals(board.getWriter(), "kim1234");
		//assertTrue : true면 테스트 통과임 ! 
		assertTrue(board.getTitle().equals("테스트11"));
		
	}
	
	// 글 번호가 1번인 글의 제목과 내용을 수정 후 다시 조회했을 때
	// 수정한 제목과 내용으로 바뀌었음을 단언하세요.
	
	@Test 
	@DisplayName ("글 번호가 1번인 글의 제목과 내용을 수정 후 다시 조회했을 때"
			+"수정한 제목과 내용으로 바뀌었음을 단언하세요")
	void updateTest() {
		//given 
		int bno = 1;
		FreeBoard board = FreeBoard.builder()
				 .bno(1)
				 .title("title 변경")
				 .content("content 변경")
				 .build();
		//when
		mapper.update(board);
		
		//then
		FreeBoard b= mapper.getContent(board.getBno());
		 assertEquals(board.getTitle(), b.getTitle());
		 assertEquals(board.getContent(), b.getContent());
				 
	}
	
	// 글 번호가 2번인 글을 삭제한 후에 전체 목록을 조회했을 떼
	// 글의 개수는 11개일 것이고
	// 2번 글을 조회했을 때 null 이 리턴됨을 단언하세요. -> assertNull (객체)
	
	
	@Test
	@DisplayName("글 번호가 2번인 글을 삭제한 후에 전체 목록을 조회했을때"
			+ "	 글의 개수는 11개일 것이고"
			+ "	2번 글을 조회했을 때 null 이 리턴됨을 단언하세요. -> assertNull (객체)")
	void delete() {
		//given
		int bno= 2;
		
		//when 
		mapper.delete(bno);
		
		//then
//		assertEquals(mapper.getList().size(), 30);
		assertNull(mapper.getContent(bno));
		
	}
	
	
}
