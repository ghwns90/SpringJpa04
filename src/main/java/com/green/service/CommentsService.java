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

	// 수정
	public CommentsDto update(Long id, CommentsDto dto) {
		
		System.out.println(dto.getArticleId());
		Long articleId = dto.getArticleId();
		
		// 1. 수정을 위한 댓글 조회
		Comments target = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("실패 수정할 댓글 없슴"));
		
		// 2. target의 내용중 수정할 내용을 변경
		target.patch(dto);
		
		// 3. 수정
		Comments updated = commentsRepository.save(target);
		
		CommentsDto commentsDto = CommentsDto.createCommentsDto(updated);
				
		//Article article = articleRepository.findById(articleId).orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패! 대상 게시물이 없습니다"));
		
		//Comments comments = Comments.updateComment(dto, article);
		
		//Comments updated = commentsRepository.save(comments);
		
		//CommentsDto newDto = CommentsDto.createCommentsDto(updated);
		
		return commentsDto;
	}

	public void delete(CommentsDto dto) {
		
		Long id = dto.getId();
		
		System.out.println("id-------------------:" + id);
		// 1. 삭제를 위한 댓글 조회
		Comments target = commentsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("삭제 실패"));
		
		// 2. 삭제	
		commentsRepository.delete(target);;
		
		//CommentsDto result = CommentsDto.createCommentsDto(target);
				
		// 성공적으로 삭제했으니 굳이 리턴필요 x
	}

}
