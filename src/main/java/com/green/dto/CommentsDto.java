package com.green.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.entity.Comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

	private Long id;				// 댓글 id
	@JsonProperty("article_id")		
	private Long articleId;			// article 의 부모글 id
	private String nickname;		// 닉네임
	private String body;			// 댓글 본문
	
	// CommentsDto <-- Comments (db 조회한)
	public static CommentsDto createCommentsDto(Comments comments) {
		return new CommentsDto(
				comments.getId(),
				comments.getArticle().getId(),
				comments.getNickname(),
				comments.getBody()
				);
	}
}
