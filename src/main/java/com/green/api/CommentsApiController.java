package com.green.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.CommentsDto;
import com.green.service.CommentsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommentsApiController {

	@Autowired
	private CommentsService commentsService;
	// 댓글
	@PostMapping("/api/articles/{articleId}/comments")
	public ResponseEntity<CommentsDto> create(@PathVariable("articleId") Long articleId,
										   @RequestBody	              CommentsDto dto) {
		log.info("dto : {}", dto);	
		
		CommentsDto created = commentsService.create(dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(created);
	}
	
	@PatchMapping("/api/articles/{id}")
	public ResponseEntity<CommentsDto> update(@PathVariable("id") Long id, @RequestBody CommentsDto commentsDto){
		
		CommentsDto updated = commentsService.update(commentsDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
}
