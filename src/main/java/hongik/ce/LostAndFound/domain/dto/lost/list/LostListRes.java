package hongik.ce.LostAndFound.domain.dto.lost.list;

import hongik.ce.LostAndFound.domain.entity.Lost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LostListRes {

    public LostListRes(Lost lost) {
        this.lostId = lost.getLostId();
        this.lostTitle = lost.getTitle();
        this.userNickname = lost.getUser().getUserNickname();

    }

    private Long lostId;
    private String lostTitle;
    private String userNickname;
}
