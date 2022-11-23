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
    public UserContentListRes(Lost lost) {
        this.id = lost.getLostId();
        this.title = lost.getTitle();
        this.date = lost.getDate();
        this.hit = lost.getHit();
    }

    public UserContentListRes(Found found) {
        this.id = found.getFoundId();
        this.title = found.getTitle();
        this.date = found.getDate();
        this.hit = found.getHit();
    }

    private Long id;
    private String title;
    private String date;

    private Long hit;
}
