package kr.inhatc.blog.user.service;

import jakarta.transaction.Transactional;
import kr.inhatc.blog.user.entity.User;
import kr.inhatc.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void join(User user) {
        userRepository.save(user);
    }
}
