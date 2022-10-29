package hongik.ce.LostAndFound.domain.dto.foundcomment;

import hongik.ce.LostAndFound.domain.entity.Found;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoundCommentRegisterReq {

    private String contents;
    private Long foundId;
    private Long userId;
}
