package hodubackspace.highschooltime.api.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseBoardList {
    private Long id;
    private String title;
    private String content;
    private int likeCount;
}
