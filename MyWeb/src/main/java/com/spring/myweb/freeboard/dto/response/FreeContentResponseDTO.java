package com.spring.myweb.freeboard.dto.response;

import java.time.LocalDateTime;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@ToString @EqualsAndHashCode

public class FreeContentResponseDTO {
	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String date;
	
	
	public FreeContentResponseDTO(FreeBoard board) {
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		if (board.getUpdateDate()==null) {
			this.date = FreeListResponseDTO.makePrettierDateString(board.getRegDate());
		}else {
			this.date = FreeListResponseDTO.makePrettierDateString(board.getUpdateDate())+"(수정됨)";
		}
	}

	

}
