package kr.inhatc.blog.member.controller;

import jakarta.validation.Valid;
import kr.inhatc.blog.member.dto.MemberFormDto;
import kr.inhatc.blog.member.entity.Member;
import kr.inhatc.blog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 폼페이지로 이동
     * @param model 회원가입 폼
     * @return 회원가입 페이지
     */
    @GetMapping("/member/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/joinForm";
    }

    /**
     * 로그인 페이지로 이동
     * @return 로그인 페이지
     */
    @GetMapping("/member/loginForm")
    public String loginForm() {
        return "member/loginForm";
    }

    /**
     * 로그인 실패시
     * @param model 로그인 실패 메시지
     * @return 로그인 페이지
     */
    @GetMapping("/member/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 패스워드를 확인해주세요.");
        return "member/loginForm";
    }

    /**
     * 회원가입
     * @param memberFormDto 회원가입 폼
     * @param bindingResult 유효성 검사
     * @param model 에러 메시지
     * @return 메인 페이지
     */
    @PostMapping(value = "/member/new")
    public String memberForm(@Valid MemberFormDto memberFormDto,
                             BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "member/joinForm";
        }
        try{
            Member member = Member.createMember(memberFormDto);
            memberService.saveMember(member);
        } catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/joinForm";
        }
        return "redirect:/";
    }
}
