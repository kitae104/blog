package kr.inhatc.blog.memo.controller;

import java.util.List;


import jakarta.validation.Valid;
import kr.inhatc.blog.memo.dto.MemoDto;
import kr.inhatc.blog.memo.entity.Memo;
import kr.inhatc.blog.memo.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import kr.inhatc.blog.memo.repository.MemoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

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
    @GetMapping("/memo/list")
    public String getMemos(Model model) {
        List<Memo> memoList = memoRepository.findAllByOrderByModifiedByDesc(); // 수정날짜 기준 최신순으로 모든 데이터 조회
        log.info(">>>> memoList : " + memoList);
        model.addAttribute("memoList", memoList);
        model.addAttribute("memoDto", new MemoDto());
        return "memo/list";
    }

    @GetMapping("/memo/list2")
    public String getMemos2(Model model) {
        List<Memo> memoList = memoRepository.findAllByOrderByModifiedByDesc(); // 수정날짜 기준 최신순으로 모든 데이터 조회
        log.info(">>>> memoList : " + memoList);
        model.addAttribute("memoList", memoList);
        return "memo/list2";
    }

    /**
     * 신규 메모 생성
     * @return
     */
    @PostMapping("/memo/add")
    public String createMemo(@Valid MemoDto memoDto, BindingResult bindingResult, Model model) {
      
      if(bindingResult.hasErrors()){
        log.info("오류 발생!!!");
        return "memo/list";
      }  
      
      try {
        log.info("memoDto : " + memoDto);
        Memo memo = new Memo(memoDto);
        memoRepository.save(memo); // 값 저장        
      } catch (Exception e) {
        model.addAttribute("errorMessage", "메모 등록 중 에러가 발생하였습니다.");
        return "memo/list"; // 오류시 상품 등록 페이지로 돌아감
      }
      
      return "redirect:memo/list";
    }

    /**
     * 메모 내용 변경
     * @param id
     * @param memoDto
     * @return
     */
    @PostMapping("/memo/update/{id}") // {id}는 아이디 값이 오는데 유동적인 값이라는 의미
    public String updateMemo(@PathVariable Long id, @RequestBody MemoDto memoDto) { // @PathVariable - {}로 감싸준 값을 가리킴
        memoService.update(id, memoDto); // 값 변경
        return "redirect:/memo/list";
    }

    /**
     * 메모 삭제
     * @param id
     * @return
     */
    @PostMapping("/memo/delete/{id}")
    public String deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id); // 값 삭제
        return "redirect:/memo/list";
    }
}
