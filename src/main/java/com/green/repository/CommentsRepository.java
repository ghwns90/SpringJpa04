package com.green.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.green.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long>{

	@Query(value="SELECT * FROM COMMENTS WHERE ARTICLE_ID =:articleId", nativeQuery = true)
	List<Comments> findByArticleId(Long articleId);

	// native query xml
	// resources 안에 META-INF 폴더를 만들고 orm.xml 파일을 만든다
	// orm.xml에 sql 을 저장해서 findByNickname() 함수 호출
	List<Comments> findByNickname(String nickname);
}
