package hongik.ce.LostAndFound.domain.dto.lostcomment;

import hongik.ce.LostAndFound.domain.entity.LostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LostCommentListRes {
    public LostCommentListRes(LostComment lostComment) {
        this.lostCommentId = lostComment.getLostCommentId();
        this.contents = lostComment.getContents();
        this.lostId = lostComment.getLost().getLostId();
        this.userNickname = lostComment.getUser().getUserNickname();
    }

    private Long lostCommentId;
    private String contents;
    private Long lostId;
    private String userNickname;
}
