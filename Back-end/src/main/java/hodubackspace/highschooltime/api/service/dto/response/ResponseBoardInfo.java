package hodubackspace.highschooltime.api.service.dto.response;

import hodubackspace.highschooltime.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thymeleaf.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ResponseBoardInfo {
    private Long id;
    private Long writerId;
    private String title;
    private String content;
    private List<ResponseBoardCommentInfo> comments = new ArrayList<>();

    public static ResponseBoardInfo of(Board board) {
        ResponseBoardInfo responseBoardInfo = new ResponseBoardInfo();
        responseBoardInfo.setId(board.getId());
        responseBoardInfo.setWriterId(board.getMember().getId());
        responseBoardInfo.setTitle(board.getTitle());
        responseBoardInfo.setContent(board.getContent());


        List<ResponseBoardCommentInfo> result = board.getComments().stream()
                .map(ResponseBoardCommentInfo::of)
                .collect(Collectors.toList());

        result.forEach(comment -> responseBoardInfo.getComments().add(comment));

        return responseBoardInfo;
    }
}
