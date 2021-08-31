package hodubackspace.highschooltime.api.controller;

import hodubackspace.highschooltime.api.common.annotation.TokenInfo;
import hodubackspace.highschooltime.api.controller.dto.request.RequestWriteBoardDto;
import hodubackspace.highschooltime.api.repository.dto.ResponseBoardList;
import hodubackspace.highschooltime.api.service.BoardService;
import hodubackspace.highschooltime.api.service.dto.response.ResponseBoardInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardName}/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Result<ResponseBoardInfo> getBoardInfo(@PathVariable("boardName") String boardName,
                                                  @PathVariable("boardId") Long boardId) {
        return new Result<>(1, boardService.viewBoardInfo(boardName, boardId));
    }

    @PostMapping("/{boardName}")
    public Result<Long> writeBoard(@PathVariable("boardName") String boardName,
                                     @RequestBody @Valid RequestWriteBoardDto request,
                                     @TokenInfo Long writerId) {


        return new Result<>(0, boardService.writeBoard(boardName, writerId, request));
    }

    @GetMapping("/{boardName}")
    @ResponseStatus(HttpStatus.OK)
    public Result<List<ResponseBoardList>> boardInfoList(@PathVariable("boardName") String boardName,
                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @TokenInfo Long memberId) {
        List<ResponseBoardList> boardLists = boardService.viewBoardList(memberId, boardName, page);
        return new Result<>(boardLists.size(), boardLists);
    }


    @Getter
    @AllArgsConstructor
    static class Result<T> {
        int count;
        T data;
    }
}
