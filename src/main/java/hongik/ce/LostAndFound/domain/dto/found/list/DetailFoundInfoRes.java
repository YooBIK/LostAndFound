package hongik.ce.LostAndFound.domain.dto.found.list;

import hongik.ce.LostAndFound.domain.entity.Found;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailFoundInfoRes {

    private String userNickname;
    private String title;
    private String category;
    private String content;
    private String date;
    private String lost_location;
    private String lost_detail;
    private String store_location;
    private String store_detail;
    private Long hit;
    
    public DetailFoundInfoRes(Found found) {
        this.userNickname = found.getUser().getUserNickname();
        this.category = found.getCategory().getCategory();
        this.title = found.getTitle();
        this.lost_location = found.getLost_location();
        this.lost_detail = found.getLost_detail();
        this.store_location = found.getStore_location();
        this.store_detail = found.getStore_detail();
        this.content = found.getContent();
        this.date = found.getDate();
        this.hit = found.getHit();
    }

}
