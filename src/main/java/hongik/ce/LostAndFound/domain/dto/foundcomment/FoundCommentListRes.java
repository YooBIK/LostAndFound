package hongik.ce.LostAndFound.domain.dto.foundcomment;

import hongik.ce.LostAndFound.domain.entity.FoundComment;
import hongik.ce.LostAndFound.domain.entity.LostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoundCommentListRes {
    private String contents;
    private String userNickname;
    private String date;

    public FoundCommentListRes(FoundComment foundComment) {
        this.userNickname = foundComment.getUser().getUserNickname();
        this.contents = foundComment.getContents();
        this.date = foundComment.getDate();

    }
}
