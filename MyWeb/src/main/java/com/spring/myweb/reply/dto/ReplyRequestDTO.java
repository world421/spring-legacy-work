package com.spring.myweb.reply.dto;

import com.spring.myweb.reply.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 *   body : JSON.stringify( {//바디에 담을거야 
                       'bno': bno,
                       'replyText': reply,
                       'replyId' :replyId,
                       'replyPw' :replyPw
                   })
 * 
 * */

@Getter @Setter @ToString
@AllArgsConstructor 
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class ReplyRequestDTO {
 // 변수 명 똑같이 써줘야 커멘트 객체가 됨 
	
	private int bno;
	private String replyText;
	private String replyId;
	private String replyPw;
	
	public Reply toEntity(ReplyRequestDTO dto) {
		return Reply.builder()
		.bno(dto.getBno())
		.replyText(dto.getReplyText())
		.replyWriter(getReplyId())
		.replyPw(dto.getReplyPw())
		.build();
		
	}
}
