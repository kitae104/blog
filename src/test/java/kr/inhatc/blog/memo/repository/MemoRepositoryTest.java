package kr.inhatc.blog.memo.repository;

import kr.inhatc.blog.memo.entity.Memo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;


    public void createMemoList(){
        for(int i = 0; i < 100; i++){
            Memo memo = Memo.builder()
                    .contents("내용 테스트" + i)
                    .userName("테스터" + i)
                    .build();
            memoRepository.save(memo);
        }
    }

    @Test
    @DisplayName("메모 생성 테스트")
    public void createMemo(){
        createMemoList();
    }
}