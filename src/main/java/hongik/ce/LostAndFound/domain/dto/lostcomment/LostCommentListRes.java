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
        this.userNickname = lostComment.getUser().getUserNickname();
        this.contents = lostComment.getContents();
        this.date = lostComment.getDate();

    }


    private String contents;
    private String userNickname;
    private String date;
}
