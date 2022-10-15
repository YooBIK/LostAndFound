package hongik.ce.LostAndFound.domain.dto.user.signup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpReq {
    private String studentNumber;
    private String userName;
    private String userEmail;
    private String userNickname;
    private String password;
}
