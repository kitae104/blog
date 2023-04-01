package kr.inhatc.blog.member.service;

import jakarta.transaction.Transactional;
import kr.inhatc.blog.member.entity.Member;
import kr.inhatc.blog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private  final MemberRepository memberRepository;

    /**
     *
     * @param member
     * @return 등록된 사용자
     */
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    /**
     * 중복 회원 검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        // 같은 이메일이 있는지 확인
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
