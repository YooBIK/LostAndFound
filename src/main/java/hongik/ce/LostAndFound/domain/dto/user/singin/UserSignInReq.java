package hongik.ce.LostAndFound.domain.dto.user.singin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInReq {
    private String studentNumber;
    private String password;
}
