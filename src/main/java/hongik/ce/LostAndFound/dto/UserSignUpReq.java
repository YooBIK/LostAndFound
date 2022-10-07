package hongik.ce.LostAndFound.dto;


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
}
