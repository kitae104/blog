package kr.inhatc.blog.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.inhatc.blog.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{

}
