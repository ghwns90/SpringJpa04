package com.green.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PatchMapping("/api/comments/{id}")
	public ResponseEntity<CommentsDto> update(@PathVariable("id") Long id, @RequestBody CommentsDto commentsDto){
		
		System.out.println("commentsDto :--------------" + commentsDto);
		
		CommentsDto updated = commentsService.update(id, commentsDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	// 댓글 삭제
	@DeleteMapping("/api/article")
	public ResponseEntity<CommentsDto> delete(@RequestBody CommentsDto dto){
		
		System.out.println("id--------------------------------------------------" + dto.getId());
		
		commentsService.delete(dto);
		
		// delete시 데이터를 반환할게 없으니 상태코드 204 No Content 만 반환한다.
		// 의미 : 요청은 성곡적으로 처리되었지만, 돌려줄 응답본문(데이터)은 없다
		return ResponseEntity.noContent().build(); 
	}
	
}
