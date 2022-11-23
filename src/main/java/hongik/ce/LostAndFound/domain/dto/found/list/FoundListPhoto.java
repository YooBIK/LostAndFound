package hongik.ce.LostAndFound.domain.dto.found.list;

import hongik.ce.LostAndFound.domain.entity.Found;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.core.io.UrlResource;
import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoundListPhoto {
    private Long foundId;
    private String title;
    private String location;
    private String userNickname;
    private Long viewNum;
    private URL url;

    public FoundListPhoto(Found found,UrlResource urlResource) {
        this.foundId = found.getFoundId();
        this.title = found.getTitle();
        this.location = found.getLost_location();
        this.userNickname = found.getUser().getUserNickname();
        this.viewNum = found.getHit();
        this.url = urlResource.getURL();

    }

}
