package com.spring.basic.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.basic.score.dto.ScoreListResponseDTO;
import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.IScoreMapper;
import com.spring.basic.score.repository.IScoreRepository;

import lombok.RequiredArgsConstructor;

//컨트롤러와 레파지토리 사이에 배치되어 기타 비즈니스 로직 처리
// ex) 값을 가공, 예외 처리, dto로 변환, 트랜잭션 등등...
@Service // 빈등록
@RequiredArgsConstructor
public class ScoreService {
	
	private final IScoreMapper scoreRepository;
	
//	@Autowired
//	public ScoreService(@Qualifier("spring") IScoreRepository scoreRepository) {
//		this.scoreRepository = scoreRepository;
//	}
	
	
	//등록 중간처리
	//컨트롤러는 나에게 DTO 를 줬어 
	//하지만 온전한 학생의 정보를 가지는객체는 ->Score(Entity)
	// 내가 Entity를 만들어서 넘겨야겠어
	
	public void insertScore(ScoreRequestDTO dto){
		//컨트롤러부터 전달받은 dto 를 보냄 
		Score score = new Score(dto);
		//Entity를 완성했으니 ,Repository에게 전달해서 DB에 넣자
		// 데이터의 조회삽입 등 = DAO
		scoreRepository.save(score);  
	}

	/*
	 * 컨트롤러는 나에게 데이터베이스를 통해
	 * 성적 정보 리스트를 가져오길 원하고 잇어.
	 * 근데 Repository는 학생 정보가 모두 포함된 리스트를 주네?
	 * 현재 요청에 어울리는 응답화면에 맞는 DTO 로변경해서주자 
	 */
	///////////////////2222222///////////////
	public List<ScoreListResponseDTO> getList() {
		List<ScoreListResponseDTO> dtoList = new ArrayList<>();
		List<Score> scoreList = scoreRepository.findAll();
		for(Score s : scoreList) {
			ScoreListResponseDTO dto = new ScoreListResponseDTO(s); //Entity에 추가 
			dtoList.add(dto); // 변환한 DTO를 DTOList에 추가 
		}
		return dtoList;
	}
	// 학생 점수 개별 조회 
	public Score retrieve(int stuNum) {
		// 응답하는 화면에 맞는 DTO 를 선언해서 주어야하는 것이 원칙.
		// 만약에 Score 전체 데이터가 필요한 것이 아니라면
		// 몇 개만 추리고 가공할 수 있는 DTO 를 설계해서 return 하는것이 맞습니다 ! 
		return scoreRepository.findByStuNum(stuNum); // findByStuNum되돌려줘라
		
	}

	public void delete(int stuNum) {
		scoreRepository.deleteByStuNum(stuNum);
		
	}
	
	public void modify(int stuNum, ScoreRequestDTO dto) {
		Score score =  scoreRepository.findByStuNum(stuNum);
		score.changeScore(dto);
		
		//dto.setName(score.getStuName()); //이름뽑아서 
		//새로운 score 객체생성해서 점수매기자! // dto 에 넣어줘 
		//Score modScore= new Score(dto); //사용자가 수정한 점수로 교환해달라 // 기존에 있는거 교체
		//modScore.setStuNum(stuNum); // 
		
		scoreRepository.modify(score);
		
		
	}
	
}
