package kr.inhatc.blog.test.controller;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
		Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 엔티티를 찾지 못했습니다."));
		return member;
	}
	
	@Transactional 	// 함수 종료시 자동 commit
	@PutMapping("/dummy/member/{id}")
	public Member updateMember(@PathVariable long id, @RequestBody Member member) { 
		System.out.println("id : " + id);
		System.out.println("Member(수정전) : " + member);
		
		Member findMember = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 엔티티를 찾지 못했습니다."));
		findMember.setPassword(member.getPassword());
		findMember.setEmail(member.getEmail());

		// 더티체킹이란?
		// JPA가 엔티티를 영속화할 때, 영속화된 엔티티를 변경하면, 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
//		Member updateMember = memberRepository.save(findMember);
//		System.out.println("Member(수정후) :" + updateMember);
		return null;
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

	/**
	 * 멤버 삭제하기
	 */
	@DeleteMapping("/dummy/member/{id}")
	public String deleteMember(@PathVariable long id) {
		System.out.println("id : " + id);
		// 예외 처리
		try {
			memberRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. id : " + id;
		}
		return "삭제되었습니다. id : " + id;
	}
}
