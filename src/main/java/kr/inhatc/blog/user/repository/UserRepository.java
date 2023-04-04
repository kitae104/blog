package kr.inhatc.blog.user.repository;

import kr.inhatc.blog.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    User login(String email, String password);
}
