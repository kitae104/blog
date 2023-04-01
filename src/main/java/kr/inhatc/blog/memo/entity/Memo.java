package kr.inhatc.blog.memo.entity;


import jakarta.persistence.*;
import kr.inhatc.blog.memo.dto.MemoDto;
import kr.inhatc.blog.utils.audit.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@NoArgsConstructor
@Getter
@Entity
@ToString
/**
 * 간단 메모 엔티티
 */
public class Memo extends BaseEntity {      // 생성,수정 시간을 자동으로 생성하도록 상속받음

    @Id
    @Column(name = "memo_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false) // 컬럼 값이 null이 아니고 반드시 값이 존재해야 함을 나타냄
    private String userName;

    @Column(nullable = false)
    private String contents;

    /**
     * 메모에 사용자 이름과 내용을 통해 등록
     * @param userName
     * @param contents
     */
    public Memo(String userName, String contents) {
        this.userName = userName;
        this.contents = contents;
    }

    /**
     * 메모에 DTO를 통해 신규 사용자와 내용 등록
     * @param memoDto
     */
    public Memo(MemoDto memoDto) {
        this.userName = memoDto.getUserName();
        this.contents = memoDto.getContents();
    }

    /**
     * 메모 사용자와 내용 수정
     * @param memoDto
     */
    public void update(MemoDto memoDto) {
        this.userName = memoDto.getUserName();
        this.contents = memoDto.getContents();
    }
}
