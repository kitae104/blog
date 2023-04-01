package kr.inhatc.blog.memo.controller;

import kr.inhatc.blog.memo.entity.Memo;
import kr.inhatc.blog.memo.repository.MemoRepository;
import kr.inhatc.blog.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@RequiredArgsConstructor    // 꼭 필요한 요소(final) 자동 생성
@Controller                 // JSON 으로 응답하기 위한 RestController 라는 의미
public class MemoController {
    private final MemoRepository memoRepository;

    private final MemoService memoService;

    /**
     * 등록된 전체 메모 목록 조회
     * @return
     */
    @GetMapping("/memo/memoList")
    public String getMemos(Model model) {
        List<Memo> memoList = memoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));; //.findAllByOrderByModifiedByDesc(); // 수정날짜 기준 최신순으로 모든 데이터 조회
        log.info(">>>> memoList : " + memoList.size());
        model.addAttribute("memoList", memoList);
        return "memo/memoList";
    }

    @GetMapping("/memo/list")
    public String getMemos2(Model model) {
        List<Memo> memoList = memoRepository.findAllByOrderByModifiedByDesc(); // 수정날짜 기준 최신순으로 모든 데이터 조회
        log.info(">>>> memoList : " + memoList);
        model.addAttribute("memoList", memoList);
        return "memo/list";
    }
}
