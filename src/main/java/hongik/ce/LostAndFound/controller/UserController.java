package hongik.ce.LostAndFound.controller;


import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.config.ResponseStatus;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpReq;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpRes;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInReq;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInRes;
import hongik.ce.LostAndFound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static hongik.ce.LostAndFound.config.ResponseStatus.*;
import java.util.List;

//@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-up")
    public Response<UserSignUpRes> userSignUp(@RequestBody UserSignUpReq userSignUpReq) {

        try{
            if(userSignUpReq.getStudentNickname().equals("") || userSignUpReq.getStudentNickname() == null){
                return new Response<>(EMPTY_STUDENT_NICKNAME);
            }
            if(userSignUpReq.getStudentEmail().equals("") || userSignUpReq.getStudentEmail() == null){
                return new Response<>(EMPTY_STUDENT_EMAIL);
            }
            if(userSignUpReq.getStudentNumber().equals("") || userSignUpReq.getStudentNumber() == null){
                return new Response<>(EMPTY_STUDENT_NUMBER);
            }
            if(userSignUpReq.getStudentName().equals("") || userSignUpReq.getStudentName() == null){
                return new Response<>(EMPTY_STUDENT_NAME);
            }
            if(userSignUpReq.getPassword().equals("") || userSignUpReq.getPassword() == null){
                return new Response<>(EMPTY_PASSWORD);
            }

            UserSignUpRes userSignUpRes = userService.userSignUp(userSignUpReq);
            return new Response<>(userSignUpRes);

        }catch(BaseException e){
            return new Response<>(e.getResponseStatus());
        }

    }

    @PostMapping(value = "/sign-in")
    public Response<UserSignInRes> userSignIn(@RequestBody UserSignInReq userSignInReq){

        try{
            if(userSignInReq.getStudentNumber().equals("")|| userSignInReq.getStudentNumber()==null){
                return new Response<>(EMPTY_STUDENT_NUMBER);
            }
            if(userSignInReq.getPassword().equals("")||userSignInReq.getPassword()==null){
                return new Response<>(EMPTY_PASSWORD);
            }
            UserSignInRes userSignInRes = userService.userSignIn(userSignInReq);
            return new Response<>(userSignInRes);
        }catch(BaseException e){
            return new Response<>(e.getResponseStatus());
        }
    }

}
