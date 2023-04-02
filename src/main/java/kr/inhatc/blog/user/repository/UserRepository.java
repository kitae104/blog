package kr.inhatc.blog.user.repository;

import kr.inhatc.blog.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
