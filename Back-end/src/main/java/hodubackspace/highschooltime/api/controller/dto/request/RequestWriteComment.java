package hodubackspace.highschooltime.api.controller.dto.request;

import hodubackspace.highschooltime.domain.Board;
import hodubackspace.highschooltime.domain.Comment;
import hodubackspace.highschooltime.domain.Member;
import lombok.Getter;

@Getter
public class RequestWriteComment {
    private String content;

    public static Comment toCommentEntity(Member writer, Board board, RequestWriteComment requestWriteComment) {
        return Comment.createCommentEntity()
                .member(writer)
                .content(requestWriteComment.getContent())
                .board(board)
                .build();
    }

}
