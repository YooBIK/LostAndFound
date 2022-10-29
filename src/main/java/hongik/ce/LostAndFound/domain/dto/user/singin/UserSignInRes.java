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
    }

    private String studentNumber;
    private String accessToken;
    private String refreshToken;
}
