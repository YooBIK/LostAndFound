package hongik.ce.LostAndFound.domain.dto.user.signup;

import hongik.ce.LostAndFound.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRes {

    public UserSignUpRes(User user) {
        this.userId = user.getUserId();
        this.studentNumber = user.getStudentNumber();
        this.userNickname = user.getUserNickname();
    }

    private Long userId;
    private String studentNumber;
    private String userNickname;

}
