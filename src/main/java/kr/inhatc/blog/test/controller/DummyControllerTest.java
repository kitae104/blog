package kr.inhatc.blog.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import kr.inhatc.blog.member.constant.Role;
import kr.inhatc.blog.member.entity.Member;
import kr.inhatc.blog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest
{
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/dummy/member/{id}")
	public Member detail(@PathVariable long id) { 
		System.out.println("id : " + id);
		Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return member;
	}
	
	@PostMapping("/dummy/join")
	public String join(Member member) {
		System.out.println("username : " + member.getUsername());
		System.out.println("password : " + member.getPassword());
		System.out.println("email : " + member.getEmail());
		
		member.setRole(Role.USER);
		
		Member mem = memberRepository.save(member);
		
		System.out.println(mem);
		return "회원가입이 완료되었습니다.";
	}
}
