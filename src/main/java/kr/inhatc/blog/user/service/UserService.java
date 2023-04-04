package kr.inhatc.blog.user.service;


import kr.inhatc.blog.user.entity.User;
import kr.inhatc.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void join(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true) // Select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
    public User login(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
