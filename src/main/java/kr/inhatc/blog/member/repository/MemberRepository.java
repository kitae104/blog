package kr.inhatc.blog.member.repository;

import kr.inhatc.blog.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    Member findByEmail(String email);
}
