package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.domain.entity.User;
import hongik.ce.LostAndFound.dto.UserSignUpReq;
import hongik.ce.LostAndFound.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final JpaUserRepository jpaUserRepository;

    public User saveUser(UserSignUpReq userSignUpReq){

        String studentName = userSignUpReq.getStudentName();
        String studentEmail = userSignUpReq.getStudentEmail();
        String studentNickname = userSignUpReq.getStudentNickname();
        String studentNumber = userSignUpReq.getStudentNumber();

        User user = new User(studentNumber,studentName,studentEmail,studentNickname);
        return jpaUserRepository.save(user);
    }
    public List<User> findAll(){
        return jpaUserRepository.findAll();
    }

}
