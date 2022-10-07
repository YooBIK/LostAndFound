package hongik.ce.LostAndFound.controller;


import hongik.ce.LostAndFound.dto.UserSignUpReq;
import hongik.ce.LostAndFound.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/sign-up")
    public String signUp(@RequestBody UserSignUpReq userSignUpReq) {

        //예외 처리 추가하기

        userService.saveUser(userSignUpReq);
        return "성공!" ;
    }
}
