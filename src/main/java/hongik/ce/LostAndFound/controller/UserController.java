package hongik.ce.LostAndFound.controller;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.config.Response;
import hongik.ce.LostAndFound.domain.dto.user.UserInfoRes;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpReq;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpRes;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInReq;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInRes;
import hongik.ce.LostAndFound.domain.dto.user.userinfo.UserContentListRes;
import hongik.ce.LostAndFound.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static hongik.ce.LostAndFound.config.ResponseStatus.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/sign-up")
    public Response<UserSignUpRes,Object> signUpUser(@RequestBody UserSignUpReq userSignUpReq){

        try{
            if(userSignUpReq.getStudentNumber().equals("") || userSignUpReq.getStudentNumber() == null){
                return new Response<>(EMPTY_STUDENT_NUMBER);
            }if(userSignUpReq.getUserName().equals("") || userSignUpReq.getUserName() == null){
                return new Response<>(EMPTY_USER_NAME);
            }if(userSignUpReq.getUserEmail().equals("") || userSignUpReq.getUserEmail() == null){
                return new Response<>(EMPTY_USER_EMAIL);
            }if(userSignUpReq.getUserNickname().equals("") || userSignUpReq.getUserNickname() == null){
                return new Response<>(EMPTY_USER_NICKNAME);
            }if(userSignUpReq.getPhoneNumber().equals("") || userSignUpReq.getPhoneNumber() == null){
                return new Response<>(EMPTY_PHONE_NUMBER);

            }if(userSignUpReq.getPassword().equals("") || userSignUpReq.getPassword() == null){
                return new Response<>(EMPTY_PASSWORD);
            }

            UserSignUpRes userSignUpRes = userService.signUpUser(userSignUpReq);
            return new Response<>(userSignUpRes);

        }catch(BaseException e){
            return new Response<>(e.getResponseStatus());
        }
    }

    @PostMapping(value = "/sign-in")
    public Response<UserSignInRes, Object> signInUser(@RequestBody UserSignInReq userSignInReq){

        try{
            if(userSignInReq.getStudentNumber().equals("") || userSignInReq.getStudentNumber() == null){
                return new Response<>(EMPTY_STUDENT_NUMBER);
            }

            if(userSignInReq.getPassword().equals("")|| userSignInReq.getPassword()==null ){
                return new Response<>(EMPTY_PASSWORD);
            }

            UserSignInRes userSignInRes = userService.signInUser(userSignInReq);
            return new Response<>(userSignInRes);

        }catch(BaseException e){
            return new Response<>(e.getResponseStatus());
        }
    }

    @GetMapping("/mypage/info/{userId}")
    public Response<UserInfoRes,Object> getUserInfo(@PathVariable Long userId){
        UserInfoRes userInfoRes;
        try{
            userInfoRes = userService.getUserInfo(userId);
        }catch (BaseException e){
            return new Response<>(e.getResponseStatus());
        }

        return new Response<>(userInfoRes);
    }

    @GetMapping("/mypage/content-info/{userId}")
    public Response<List<UserContentListRes>,List<UserContentListRes>> getUserContent(@PathVariable Long userId){
        List<UserContentListRes> lostList = new ArrayList<>();
        List<UserContentListRes> foundList = new ArrayList<>();
        try{
            lostList = userService.getUserLostList(userId);
            foundList = userService.getUserFoundList(userId);
        }catch (BaseException e){
            return new Response<>(e.getResponseStatus());
        }
        return new Response<>(lostList,foundList);

    }




}
