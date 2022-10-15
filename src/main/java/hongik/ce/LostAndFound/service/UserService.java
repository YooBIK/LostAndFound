package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpRes;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInReq;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInRes;
import hongik.ce.LostAndFound.domain.entity.User;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpReq;
import hongik.ce.LostAndFound.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static hongik.ce.LostAndFound.config.ResponseStatus.*;
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final JpaUserRepository jpaUserRepository;

    public UserSignUpRes signUpUser(UserSignUpReq userSignUpReq) throws BaseException {

        String studentNumber = userSignUpReq.getStudentNumber();
        String password = userSignUpReq.getPassword();
        String userName = userSignUpReq.getUserName();
        String userEmail = userSignUpReq.getUserEmail();
        String userNickname = userSignUpReq.getUserNickname();

        if(jpaUserRepository.existsByStudentNumber(studentNumber)){
            throw new BaseException(ALREADY_EXIST_ACCOUNT);
        }

        User user = new User(studentNumber,password,userName,userEmail,userNickname);
        User result;

        try{
            result = jpaUserRepository.save(user);
        }catch(Exception e){
            throw new BaseException(DATABASE_ERROR);
        }

        return new UserSignUpRes(result);
    }

    public UserSignInRes signInUser(UserSignInReq userSignInReq) throws BaseException{
        String studentNumber = userSignInReq.getStudentNumber();
        String password = userSignInReq.getPassword();


        if(!jpaUserRepository.existsByStudentNumber(studentNumber)){
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }

        User user;
        try{
            user = jpaUserRepository.findByStudentNumber(studentNumber);
        }catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }

        if(user.getPassword().equals(password)){
            return new UserSignInRes(user);
        }else{
            throw new BaseException(INVALID_PASSWORD);
        }
    }
}
