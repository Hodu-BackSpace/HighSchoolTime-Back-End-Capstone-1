package hodubackspace.highschooltime.api.repository.board.query.dto;

import hodubackspace.highschooltime.api.service.dto.response.ResponseBoardCommentInfo;
import hodubackspace.highschooltime.domain.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResponseBoardList {
    private Long id;
    private String title;
    private String content;
    private int likeCount;
    private List<ResponseBoardCommentInfo> comments = new ArrayList<>();

    public ResponseBoardList(Long id, String title, String content, int likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
    }

    public static ResponseBoardList of(Board board) {
        ResponseBoardList responseBoardList = new ResponseBoardList(board.getId(), board.getTitle(), board.getContent(), board.getLikeCount());
        List<ResponseBoardCommentInfo> commentDtos = board.getComments().stream()
                .map(ResponseBoardCommentInfo::of)
                .collect(Collectors.toList());

        commentDtos.forEach(commentDto -> responseBoardList.getComments().add(commentDto));

        return responseBoardList;
    }
}
