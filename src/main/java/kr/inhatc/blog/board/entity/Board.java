package kr.inhatc.blog.board.entity;

import jakarta.persistence.*;
import kr.inhatc.blog.member.entity.Member;
import kr.inhatc.blog.utils.audit.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board extends BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int count;
	

	@ManyToOne(fetch = FetchType.LAZY)   // Many = Board, One = Member 
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)  // 연관 관계의 주인이 아니라 DB에 컬럼이 만들어지지 않음. 
	private List<Reply> reply;
	
}
