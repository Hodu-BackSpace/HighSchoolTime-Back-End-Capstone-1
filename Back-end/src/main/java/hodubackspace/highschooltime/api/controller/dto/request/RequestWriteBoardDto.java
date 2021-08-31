package hodubackspace.highschooltime.api.controller.dto.request;

import hodubackspace.highschooltime.domain.Board;
import hodubackspace.highschooltime.domain.BoardGroup;
import hodubackspace.highschooltime.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class RequestWriteBoardDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public static Board toBoardEntity(BoardGroup boardGroup, Member writer, RequestWriteBoardDto requestDto) {
        return Board.createBoardEntity()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .boardGroup(boardGroup)
                .member(writer)
                .build();
    }

}
