package hongik.ce.LostAndFound.domain.dto.found.list;

import hongik.ce.LostAndFound.domain.entity.Found;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoundListRes {
    private Long foundId;
    private String title;
    private String location;
    private String userNickname;
    private Long viewNum;

    public FoundListRes(Found found) {
        this.foundId = found.getFoundId();
        this.title = found.getTitle();
        this.location = found.getLost_location();
        this.userNickname = found.getUser().getUserNickname();
        this.viewNum = found.getHit();

    }

}
