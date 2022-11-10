package hongik.ce.LostAndFound.domain.dto.found.register;

import hongik.ce.LostAndFound.domain.entity.Found;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class FoundRegisterRes {
    private Long foundId;
    private String title;

    public FoundRegisterRes(Found found) {
        this.foundId = found.getFoundId();
        this.title = found.getTitle();
    }
}
