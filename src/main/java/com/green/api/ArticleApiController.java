package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.ArticleDto;
import com.green.entity.Article;
import com.green.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ArticleApiController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping(value="/api/articles", produces = "application/json;charset=UTF-8")
	public List<Article> list(){
		
		List<Article> list = articleService.getList();
		log.info("list",list);
		
		return list;
	}
	
	//ResponseEntity<Articles>
	// Article : DATA
	// + 상태코드 ResponseEntity.status(HttpStatus.ok)
	// 		 또는 ResponseEntity.status(HttpStatus.BAD_REQUEST)
	
	// 한개 조회
	@GetMapping("/api/articles/{id}")
	public ResponseEntity<Article> getOne(@PathVariable Long id){
		
		Article article = articleService.getOne(id);
		
		ResponseEntity<Article> result = 
				(article != null)
				? ResponseEntity.status(HttpStatus.OK).body(article)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return result;
	}
	
	@PostMapping("/api/articles")
	public ResponseEntity<Article> create( @RequestBody ArticleDto articleDto ) {	
		// @RequestBody : 넘어오는 정보는 json 문자열
		
		Article created = articleService.create(articleDto);
		
		ResponseEntity<Article> result = 
				(created != null ) ? 
						ResponseEntity.status(HttpStatus.OK).body(created) // 200
						: ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
				
		return result;
	}
	
	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Article> delete(@PathVariable("id") Long id){
		
		System.out.println("id" + id);
		
		Article deleted = articleService.delete(id);
		
		ResponseEntity<Article> result = 
				(deleted != null ) ? 
						ResponseEntity.status(HttpStatus.OK).body(deleted) // 200
						: ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
		
		return result;
	}
	
	// 수정
	// Put : 전체 데이터를 수정 -> 모든 데이터가 필요
	// Patch : 전체, 일부 데이터를 수정 
	@PatchMapping("/api/articles")
	public ResponseEntity<Article> update(@RequestBody Article article ){ // <- JSON.String){
		
		Article updated = articleService.update(article);
		ResponseEntity<Article> result = 
				(updated != null)
				? ResponseEntity.status(HttpStatus.OK).body(updated)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return result;
	
	}
	
	// 3줄의 data
	/*
	 * [
		{"title" : "시간예약", "content" : "1420" },
		{"title" : "영화지정", "content" : "케데헌" },
		{"title" : "자리지정", "content" : "A2" }
	   ]
	*/
	// /api/transaction-test1
	// 3개의 데이터를 받아서 서비스에 넘겨 저장결과를 받는다
	@PostMapping("/api/transaction-test1")
	public ResponseEntity<List<Article>> transaction1(@RequestBody List<ArticleDto> dtos){
		
		List<Article> createdList = articleService.createArticleList(dtos);
		
		ResponseEntity<List<Article>> result =
				(createdList != null)
				? ResponseEntity.status(HttpStatus.OK).body(createdList)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();		
		
		return result;
		
	}
	
	// 3줄의 data
		/*
		 * [
			{"title" : "시간예약", "content" : "1420" },
			{"title" : "영화지정", "content" : "케데헌" },
			{"title" : "자리지정", "content" : "A2" }
		   ]
		*/
		// /api/transaction-test1
		// 3개의 데이터를 받아서 서비스에 넘겨 저장결과를 받는다
		@PostMapping("/api/transaction-test2")
		public ResponseEntity<List<Article>> transaction2(@RequestBody List<ArticleDto> dtos){
			
			List<Article> createdList = articleService.createArticleList2(dtos);
			
			ResponseEntity<List<Article>> result =
					(createdList != null)
					? ResponseEntity.status(HttpStatus.OK).body(createdList)
					: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();		
			
			return result;
			
		}
	
}
