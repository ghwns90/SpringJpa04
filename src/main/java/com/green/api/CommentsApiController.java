package com.green.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.Comments;
import com.green.dto.CommentsDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommentsApiController {

	// 댓글
	@PostMapping("/api/articles/{articleId}/comments")
	public ResponseEntity<Comments> create(@PathVariable("articleId") Long articleId,
										   @RequestBody	              CommentsDto dto) {
		log.info("dto : {}", dto);	
		return null;
	}
	
}
