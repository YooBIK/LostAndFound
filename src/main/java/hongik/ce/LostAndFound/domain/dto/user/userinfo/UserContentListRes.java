package hongik.ce.LostAndFound.domain.dto.user.userinfo;

import hongik.ce.LostAndFound.domain.entity.Found;
import hongik.ce.LostAndFound.domain.entity.Lost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserContentListRes {
    public UserContentListRes(Lost lost ) {
        this.title = lost.getTitle();
        this.date = lost.getDate();
    }

    public UserContentListRes(Found found) {
        this.title = found.getTitle();
        this.date = found.getDate();
    }

    private String title;
    private String date;
}
