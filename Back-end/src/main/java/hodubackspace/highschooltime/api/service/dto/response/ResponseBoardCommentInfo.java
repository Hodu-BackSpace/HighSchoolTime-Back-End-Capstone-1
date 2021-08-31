package hodubackspace.highschooltime.api.service.dto.response;

import hodubackspace.highschooltime.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseBoardCommentInfo {
    private String content;
    private Long writer;

    public static ResponseBoardCommentInfo of(Comment comment) {
        return new ResponseBoardCommentInfo(comment.getContent(), comment.getMember().getId());
    }
}
