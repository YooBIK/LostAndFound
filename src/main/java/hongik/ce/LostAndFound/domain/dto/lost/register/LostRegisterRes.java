package hongik.ce.LostAndFound.domain.dto.lost.register;

import hongik.ce.LostAndFound.domain.entity.Lost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class LostRegisterRes {
    private Long lostId;
    private String title;

    public LostRegisterRes(Lost lost) {
        this.lostId = lost.getLostId();
        this.title = lost.getTitle();
    }
}
