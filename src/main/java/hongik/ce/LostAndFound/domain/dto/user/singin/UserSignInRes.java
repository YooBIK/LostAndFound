package hongik.ce.LostAndFound.domain.dto.user.singin;

import hongik.ce.LostAndFound.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInRes {

    public UserSignInRes(User user) {
        this.studentNumber = user.getStudentNumber();
        this.userId = user.getUserId();
    }

    private String studentNumber;
    private Long userId;
}
