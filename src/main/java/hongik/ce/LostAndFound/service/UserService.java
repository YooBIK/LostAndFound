package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpRes;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInReq;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInRes;
import hongik.ce.LostAndFound.domain.entity.User;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpReq;
import hongik.ce.LostAndFound.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static hongik.ce.LostAndFound.config.ResponseStatus.*;
@Service
@Transactional
public class UserService {

    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserService(JpaUserRepository jpaUserRepository)  {
        this.jpaUserRepository = jpaUserRepository;
    }

    public UserSignUpRes userSignUp(UserSignUpReq userSignUpReq) throws BaseException {

        String studentName = userSignUpReq.getStudentName();
        String studentEmail = userSignUpReq.getStudentEmail();
        String studentNickname = userSignUpReq.getStudentNickname();
        String studentNumber = userSignUpReq.getStudentNumber();
        String password = userSignUpReq.getPassword();

        Long flag;
        try{
            flag = jpaUserRepository.countByStudentNumber(studentNumber);
        }catch(Exception e){
            throw new BaseException(DATABASE_ERROR);
        }

        if(flag>0){
            throw new BaseException(ALREADY_EXIST_ACCOUNT);
        }

        User user = new User(studentNumber,studentName,studentEmail,studentNickname,password);
        UserSignUpRes userSignUpRes;
        try{
            userSignUpRes = new UserSignUpRes(jpaUserRepository.save(user));
        }catch(Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
        return userSignUpRes;
    }

    public UserSignInRes userSignIn(UserSignInReq userSignInReq) throws BaseException{
        String studentNumber = userSignInReq.getStudentNumber();
        String password = userSignInReq.getPassword();
        User user;
        try{
            user = jpaUserRepository.findByStudentNumber(studentNumber);
        }catch(Exception e){
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }

        if(user.getPassword().equals(password)){
            return new UserSignInRes(user);
        }else{
            throw new BaseException(INVALID_PASSWORD);
        }
    }
}
