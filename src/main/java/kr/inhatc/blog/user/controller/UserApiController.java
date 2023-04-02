package kr.inhatc.blog.user.controller;

import kr.inhatc.blog.member.constant.Role;
import kr.inhatc.blog.user.entity.User;
import kr.inhatc.blog.user.service.UserService;
import kr.inhatc.blog.utils.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : " + user );
        // 실제로 DB에 insert를 하고 아래에서 return이 되면 됩니다.
        user.setRole(Role.USER);
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
