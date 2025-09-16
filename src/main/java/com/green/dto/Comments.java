package com.green.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	// @Getter, @Setter, @ToString, @equals, @hashcode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "COMMENTS_SEQ_GENERATOR",
		sequenceName = "COMMENTS_SEQ",	// create sequence
		initialValue = 1,	// 초기값 start with 1
		allocationSize = 1	// 증가치 increment by 1
)
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTS_SEQ_GENERATOR")
	private Long id;
	
	// 오라클 11g varchar2 최대 4000, -> CLOB
	// 오라클 12c varchar2 최대 32000 -> 별도설정 필요
	@Column
	private String body;
	@Column
	private String nickname;
	
	// 외래키 설정
	
	@ManyToOne							// 외래키 설정
	@JoinColumn(name="article_id")		// 외래키 컬럼 이름 설정
	private Article article;
}
