package com.green.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.ArticleDto;
import com.green.entity.Article;
import com.green.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("articleService")
public class ArticleService {
	
	@Autowired
    ArticleRepository articleRepository;
	
	public List<Article> getList(){
		
		List<Article> articleList = articleRepository.findAll();
		
		return articleList;
	}
	
	public Article create(ArticleDto articleDto) {
	
		Article article = articleDto.toEntity();
		// 입력된 id가 있으면 안됨, 자동증가
		if(article.getId() != null)
			return null;
		
		Article saved = articleRepository.save(article);
		
		return saved;
	
	}

	public Article getOne(Long id) {
		
		Article article = articleRepository.findById(id).orElse(null);
		//log.info(article.toString());
		return article;
	}

	public Article delete(Long id) {
		
		// 삭제할 때는 미리 검색을 하고 cache memory 에서 검색해보고
		Article target = articleRepository.findById(id).orElse(null);
		if(target == null)
			return null;
		
		articleRepository.delete(target);
		
		return target;
	}

	public Article update(Article article) {
		
		// 0. 수정할 자료의 id
		Long id = article.getId();
		// 1. 수정할 데이터를 검색
		Article target = articleRepository.findById(id).orElse(null);
		// 2. target 이 null 이면
		if( target == null || target.getId() != id) {
			log.info("id:{} article:{}", id, article );
			return null; // 잘못된 요청 400
		}
		
		// 3. 업데이트 (JPA -> .save())  
		Article updated = articleRepository.save(article);
		
		return updated;
	}
////////////////////////////////////////////////////////////////////////////////////
	// 에러 발생 시켜서 실패 시키기
	// 탈렌드api 로 실험
	// @transactional 이 없으면 에러가 떴어도 DB에 데이터가 저장됨
	public List<Article> createArticleList(List<ArticleDto> dtos) {
		
		// 1. 넘어온 Dto 들을 Article 엔티티 묶음으로 변환
		List<Article> articleList = new ArrayList<>();
		for(ArticleDto dto : dtos) {
			
			Article article = dto.toEntity();
			articleList.add(article);
			
		}
		
		// 2. db 에 반복 저장
		for (Article article : articleList) {
			articleRepository.save(article);
		}
		
		// 3. 강제로 에러 발생 - 찾다가 없으면 예외발생
		articleRepository.findById(-1L).orElseThrow(()-> new IllegalArgumentException("결제 실패"));
		
		return articleList;
	}
	
	// 에러 발생 시켜서 실패 시키기
	// 탈렌드api 로 실험
	// @transactional 이 있기 때문에 DB에 저장 안됨
	@Transactional
	public List<Article> createArticleList2(List<ArticleDto> dtos) {
		
		// 1. 넘어온 Dto 들을 Article 엔티티 묶음으로 변환
		List<Article> articleList = new ArrayList<>();
		for(ArticleDto dto : dtos) {
			
			Article article = dto.toEntity();
			articleList.add(article);
			
		}
		
		// 2. db 에 반복 저장
		for (Article article : articleList) {
			articleRepository.save(article);
		}
		
		// 3. 강제로 에러 발생 - 찾다가 없으면 예외발생
		articleRepository.findById(-1L).orElseThrow(()-> new IllegalArgumentException("결제 실패"));
		
		return articleList;
	}
}
