package kr.inhatc.blog.user.controller;

import jakarta.servlet.http.HttpSession;
import kr.inhatc.blog.member.constant.Role;
import kr.inhatc.blog.user.entity.User;
import kr.inhatc.blog.user.service.UserService;
import kr.inhatc.blog.utils.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        log.info("UserApiController : " + user );
        // 실제로 DB에 insert를 하고 아래에서 return이 되면 됩니다.
        user.setRole(Role.USER);
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
        log.info("UserApiController : " + user );

        User principal = userService.login(user);   // principal (접근주체)
        if(principal != null) {
            session.setAttribute("principal", principal);     // 세션에 principal을 저장
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
