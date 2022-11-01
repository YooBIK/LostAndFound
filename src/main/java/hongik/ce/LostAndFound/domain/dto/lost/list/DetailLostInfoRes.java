package hongik.ce.LostAndFound.domain.dto.lost.list;


import hongik.ce.LostAndFound.domain.entity.Category;
import hongik.ce.LostAndFound.domain.entity.Lost;
import hongik.ce.LostAndFound.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailLostInfoRes {
    public DetailLostInfoRes(Lost lost) {
        this.userNickname = lost.getUser().getUserNickname();
        this.title = lost.getTitle();
        this.contents = lost.getContents();
        this.date = lost.getDate();
        this.hit = lost.getHit();
        this.location = lost.getLocation();
    }

    private String userNickname;
    private String title;
    private String contents;
    private String date;
    private String location;
    private Long hit;


}
