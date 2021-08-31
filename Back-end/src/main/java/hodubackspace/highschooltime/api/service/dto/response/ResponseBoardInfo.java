package hodubackspace.highschooltime.api.service.dto.response;

import hodubackspace.highschooltime.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thymeleaf.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseBoardInfo {
    private Long id;
    private String title;
    private String content;
    private List<ResponseBoardCommentInfo> comments;

    public static ResponseBoardInfo of(Board board) {
        ResponseBoardInfo responseBoardInfo = new ResponseBoardInfo();
        responseBoardInfo.setId(board.getId());
        responseBoardInfo.setTitle(board.getTitle());
        responseBoardInfo.setContent(board.getContent());


        board.getComments().stream()
                .map(ResponseBoardCommentInfo::of)
                .map((ResponseBoardCommentInfo) -> responseBoardInfo.getComments().add(ResponseBoardCommentInfo));

        if (responseBoardInfo.getComments() == null) {
            responseBoardInfo.setComments(Collections.emptyList());
        }

        return responseBoardInfo;
    }
}
