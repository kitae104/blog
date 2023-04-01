package kr.inhatc.blog.memo.service;

import kr.inhatc.blog.memo.dto.MemoDto;
import kr.inhatc.blog.memo.entity.Memo;
import kr.inhatc.blog.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@Service // 이 클래스가 서비스임을 알려줌
public class MemoService {

    // final 은 꼭 필요한 요소임을 명시하는 것, 값이 변경 될 수 없음
    private final MemoRepository memoRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌, 자동으로 DB에 업데이트 됨
    public Long update(Long id, MemoDto memoDto) {

        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        memo.update(memoDto);

        return memo.getId();
    }
}
