package com.spring.myweb.freeboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.FreeContentResponseDTO;
import com.spring.myweb.freeboard.dto.FreeListResponseDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.dto.FreeUpdateRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

import lombok.RequiredArgsConstructor;

@Service // 서비스 선언해야 컨트롤러에서 주입받을수있음
@RequiredArgsConstructor
public class FreeBoardService implements IFreeBoardService {

	private final IFreeBoardMapper mapper;
	
	@Override
	public void regist(FreeRegistRequestDTO dto) {
		mapper.regist(FreeBoard.builder()
				.title(dto.getTitle())
				.writer(dto.getWriter())
				.content(dto.getContent())
				.build());
		

	}

	@Override
	public List<FreeListResponseDTO> getList() {
		List<FreeListResponseDTO> dtoList=new ArrayList<>();
		List<FreeBoard> list = mapper.getList();
		for(FreeBoard board : list ) {
			dtoList.add(new FreeListResponseDTO(board));
		}
		return dtoList;
	}

	@Override
	//dto 는 서비승쪽에서만 씀 !  
	public FreeContentResponseDTO getContent(int bno) {
		return new FreeContentResponseDTO(mapper.getContent(bno));

	}

	@Override
	public void update(FreeUpdateRequestDTO dto) {
		mapper.update(FreeBoard.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.bno(dto.getBno())
				.build());
	}

	@Override
	public void delete(int bno) {
		mapper.delete(bno);

	}

}
