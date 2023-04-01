package kr.inhatc.blog.memo.controller;

import kr.inhatc.blog.memo.dto.MemoDto;
import kr.inhatc.blog.memo.entity.Memo;
import kr.inhatc.blog.memo.repository.MemoRepository;
import kr.inhatc.blog.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor    // 꼭 필요한 요소(final) 자동 생성
@RestController             // JSON 으로 응답하기 위한 RestController 라는 의미
public class MemoRestController {
  private final MemoRepository memoRepository;
  private final MemoService memoService;

  // 신규 메모 생성
  @PostMapping("/api/memos")
  public Memo createMemo(@RequestBody MemoDto requestDto) {
      Memo memo = new Memo(requestDto);
      return memoRepository.save(memo); // 값 저장
  }

  // 등록된 전체 메모 목록 조회
  @GetMapping("/api/memos")
  public List<Memo> getMemos() {
      return memoRepository.findAllByOrderByModifiedByDesc(); // 수정날짜 기준 최신순으로 모든 데이터 조회
  }

  // 메모 내용 변경
  @PutMapping("/api/memos/{id}") // {id}는 아이디 값이 오는데 유동적인 값이라는 의미
  public Long updateMemo(@PathVariable Long id, @RequestBody MemoDto requestDto) { // @PathVariable - {}로 감싸준 값을 가리킴
      memoService.update(id, requestDto); // 값 변경
      return id;
  }

  // 메모 삭제
  @DeleteMapping("/api/memos/{id}")
  public Long deleteMemo(@PathVariable Long id) {
      memoRepository.deleteById(id); // 값 삭제
      return id;
  }
}
