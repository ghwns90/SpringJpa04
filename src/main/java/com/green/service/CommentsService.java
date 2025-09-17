package com.green.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.CommentsDto;
import com.green.entity.Article;
import com.green.entity.Comments;
import com.green.repository.ArticleRepository;
import com.green.repository.CommentsRepository;

@Service
public class CommentsService {

	@Autowired
	private CommentsRepository commentsRepository;
	@Autowired
	private ArticleRepository articleRepository;
	public List<Comments> getComments(Long id) {
		
		// ArticleId로 조회
		List<Comments> comments = commentsRepository.findByArticleId(id);
		
		// Nickname 으로 조회 (xml파일 로 조회)
		//List<Comments> comments = commentsRepository.findByNickname("Kim");
		System.out.println(comments);
		
		return comments;
	}

	// 댓글 추가
	public CommentsDto create(CommentsDto dto) {
		
		Long articleId = dto.getArticleId();
		// 1. 게시글 조회 및 조회실패 예외발생
		// 게시글에 존재하지 않는 articleId가 넘어오면 Throw(예외를 던진다)
		Article article = articleRepository.findById(articleId).orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패! 대상 게시물이 없습니다"));
		
		// 2. 댓글 엔티티 생성 -> 저장할 데이터를 만든다
		Comments comments = Comments.createComment(dto, article);
		// 3. 댓글 엔티티를 db에 저장
		Comments created = commentsRepository.save(comments);
		
		CommentsDto newDto = CommentsDto.createCommentsDto(created);	
		return newDto;
	}

	public CommentsDto update(CommentsDto commentsDto) {
		
		Long articleId = commentsDto.getArticleId();
		
		Article article = articleRepository.findById(articleId).orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패! 대상 게시물이 없습니다"));
		
		Comments comments = Comments.createComment(commentsDto, article);
		
		Comments updated = commentsRepository.save(comments);
		
		CommentsDto newDto = CommentsDto.createCommentsDto(updated);
		
		return newDto;
	}

}
