package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.config.BaseException;
import hongik.ce.LostAndFound.domain.dto.user.UserInfoRes;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpReq;
import hongik.ce.LostAndFound.domain.dto.user.signup.UserSignUpRes;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInReq;
import hongik.ce.LostAndFound.domain.dto.user.singin.UserSignInRes;
import hongik.ce.LostAndFound.domain.dto.user.userinfo.UserContentListRes;
import hongik.ce.LostAndFound.domain.entity.Found;
import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.domain.entity.User;
import hongik.ce.LostAndFound.repository.JpaFoundRepository;
import hongik.ce.LostAndFound.repository.JpaLostRepository;
import hongik.ce.LostAndFound.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static hongik.ce.LostAndFound.config.ResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final JpaUserRepository jpaUserRepository;
    private final JpaFoundRepository jpaFoundRepository;
    private final JpaLostRepository jpaLostRepository;

    public UserSignUpRes signUpUser(UserSignUpReq userSignUpReq) throws BaseException {

        String studentNumber = userSignUpReq.getStudentNumber();
        String password = userSignUpReq.getPassword();
        String userName = userSignUpReq.getUserName();
        String userEmail = userSignUpReq.getUserEmail();
        String userNickname = userSignUpReq.getUserNickname();
        String userPhoneNumber = userSignUpReq.getPhoneNumber();
        if (jpaUserRepository.existsByStudentNumber(studentNumber)) {
            throw new BaseException(ALREADY_EXIST_ACCOUNT);
        }

        User user = new User(studentNumber, password, userName, userEmail, userNickname, userPhoneNumber);
        User result;

        try {
            result = jpaUserRepository.save(user);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

        return new UserSignUpRes(result);
    }

    public UserSignInRes signInUser(UserSignInReq userSignInReq) throws BaseException {
        String studentNumber = userSignInReq.getStudentNumber();
        String password = userSignInReq.getPassword();

        if (!jpaUserRepository.existsByStudentNumber(studentNumber)) {
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }

        User user;
        try {
            user = jpaUserRepository.findByStudentNumber(studentNumber);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

        if (user.getPassword().equals(password)) {
            return new UserSignInRes(user);
        } else {
            throw new BaseException(INVALID_PASSWORD);
        }
    }

    public UserInfoRes getUserInfo(Long userId) throws BaseException {
        User findUser;
        try {
            findUser = jpaUserRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }
        return new UserInfoRes(findUser);
    }

    public List<UserContentListRes> getUserLostList(Long userId) throws BaseException {
        List<Lost> lostList;
        User findUser;
        try {
            findUser = jpaUserRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }

        try {
            lostList = jpaLostRepository.findAllByUser(findUser);
        } catch (Exception e) {
            throw new BaseException(NOT_EXIST_LOST);
        }
        List<UserContentListRes> result = new ArrayList<>();
        for (Lost l : lostList) {
            result.add(new UserContentListRes(l));
        }
        return result;
    }

    public List<UserContentListRes> getUserFoundList(Long userId) throws BaseException {
        List<Found> foundList = new ArrayList<>();
        User findUser;
        try {
            findUser = jpaUserRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new BaseException(NOT_EXIST_ACCOUNT);
        }

        try {
            foundList = jpaFoundRepository.findAllByUser(findUser);
        } catch (Exception e) {
            throw new BaseException(NOT_EXIST_LOST);
        }
        List<UserContentListRes> result = new ArrayList<>();
        for (Found f : foundList) {
            result.add(new UserContentListRes(f));
        }
        return result;
    }
}
