package hongik.ce.LostAndFound.domain.dto.lostcomment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LostCommentRegisterReq {

    private String contents;
    private Long lostId;
    private Long userId;

}
