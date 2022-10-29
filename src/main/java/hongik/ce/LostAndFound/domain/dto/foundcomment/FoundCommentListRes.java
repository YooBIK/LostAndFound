package hongik.ce.LostAndFound.domain.dto.foundcomment;

import hongik.ce.LostAndFound.domain.entity.FoundComment;
import hongik.ce.LostAndFound.domain.entity.LostComment;

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
