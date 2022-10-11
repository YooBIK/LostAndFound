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
        this.id = user.getStudentNumber();
        this.jwt = "jwt";
    }

    private String id;
    private String jwt;
}
