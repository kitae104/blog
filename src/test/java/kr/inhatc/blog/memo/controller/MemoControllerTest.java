package kr.inhatc.blog.memo.controller;

import jakarta.transaction.Transactional;
import kr.inhatc.blog.memo.repository.MemoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class MemoControllerTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    @DisplayName("메모 생성 테스트")
    void createMemo() {
        // given
        // when
        // then
    }

}