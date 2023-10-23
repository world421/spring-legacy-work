package com.spring.myweb.snsboard.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor 
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class SnsBoardRequestDTO {

	// 자바 스크립트에 있는 fetch ~ 
	private String content;
	private String writer;
	private MultipartFile file;
	
	
	
}
