package kr.inhatc.blog.test.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/dummy/members")
	public List<Member> list() { 
		return memberRepository.findAll();
	}
	
	
	@GetMapping("/dummy/member") 
	public List<Member> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC)Pageable pageable) { 
		
		Page<Member> pagingMember =  memberRepository.findAll(pageable);
		List<Member> members = pagingMember.getContent();
		return members;
	}
	
	@GetMapping("/dummy/member/{id}")
	public Member detail(@PathVariable long id) { 
		System.out.println("id : " + id);
		Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return member;
	}
	
	@Transactional
	@PutMapping("/dummy/member/{id}")
	public Member updateMember(@PathVariable long id, @RequestBody Member member) { 
		System.out.println("id : " + id);
		System.out.println("Member(수정전) : " + member);
		
		Member findMember = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 엔티티를 찾지 못했습니다."));
		findMember.setPassword(member.getPassword());
		findMember.setEmail(member.getEmail());
		
		Member updateMember = memberRepository.save(findMember);
		System.out.println("Member(수정후) :" + updateMember);
		return updateMember;
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
