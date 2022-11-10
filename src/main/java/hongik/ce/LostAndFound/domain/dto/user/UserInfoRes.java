package hongik.ce.LostAndFound.domain.dto.user;

import hongik.ce.LostAndFound.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRes {
    public UserInfoRes(User user) {
        this.studentNumber= user.getStudentNumber();
        this.name = user.getUserName();
        this.email = user.getUserEmail();
        this.phoneNumber = user.getUserPhoneNumber();
    }

    private String studentNumber;
    private String name;
    private String email;
    private String phoneNumber;

}
