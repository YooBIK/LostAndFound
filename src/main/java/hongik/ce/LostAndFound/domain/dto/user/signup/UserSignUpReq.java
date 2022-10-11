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
    private String studentName;
    private String studentEmail;
    private String studentNickname;
    private String password;
}
