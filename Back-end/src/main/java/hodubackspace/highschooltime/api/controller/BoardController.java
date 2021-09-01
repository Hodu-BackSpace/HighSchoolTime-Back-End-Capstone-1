package hodubackspace.highschooltime.api.controller;

import hodubackspace.highschooltime.api.common.annotation.TokenInfo;
import hodubackspace.highschooltime.api.controller.dto.ResponseResultDto;
import hodubackspace.highschooltime.api.controller.dto.request.RequestWriteBoardDto;
import hodubackspace.highschooltime.api.controller.dto.request.RequestWriteComment;
import hodubackspace.highschooltime.api.repository.board.query.dto.ResponseBoardList;
import hodubackspace.highschooltime.api.service.BoardService;
import hodubackspace.highschooltime.api.service.CommentService;
import hodubackspace.highschooltime.api.service.dto.response.ResponseBoardInfo;
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
    private final CommentService commentService;

    @GetMapping("/{boardName}/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseResultDto<ResponseBoardInfo> getBoardInfo(@PathVariable("boardName") String boardName,
                                                  @PathVariable("boardId") Long boardId) {
        return new ResponseResultDto<>(1, boardService.viewBoardInfo(boardName, boardId));
    }

    @PostMapping("/{boardName}/{boardId}/like")
    public ResponseResultDto<String> clickLike(@PathVariable("boardId") Long boardId,
                                    @TokenInfo Long memberId) {
        boardService.increaseLikeCount(memberId, boardId);

        return new ResponseResultDto<>(0, "Success Increase Like Count");
    }

    @PostMapping("/{boardName}/{boardId}/comment")
    public ResponseResultDto<String> writeComment(@RequestBody @Valid RequestWriteComment request,
                                       @PathVariable("boardId") Long boardId,
                                       @TokenInfo Long memberId) {

        commentService.writeComment(memberId, boardId, request);

        return new ResponseResultDto<>(0, "Success Write Comment");
    }

    @DeleteMapping("/{boardName}/{boardId}/comment/{commentId}")
    public ResponseResultDto<String> deleteComment(@PathVariable("commentId") Long commentId,
                                        @TokenInfo Long memberId) {
        commentService.deleteComment(memberId,commentId);

        return new ResponseResultDto<>(0, "Success Delete Comment");
    }

    @PostMapping("/{boardName}")
    public ResponseResultDto<Long> writeBoard(@PathVariable("boardName") String boardName,
                                     @RequestBody @Valid RequestWriteBoardDto request,
                                     @TokenInfo Long writerId) {


        return new ResponseResultDto<>(0, boardService.writeBoard(boardName, writerId, request));
    }

    @GetMapping("/{boardName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseResultDto<List<ResponseBoardList>> boardInfoList(@PathVariable("boardName") String boardName,
                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @TokenInfo Long memberId) {
        List<ResponseBoardList> boardLists = boardService.viewBoardList(memberId, boardName, page);
        return new ResponseResultDto<>(boardLists.size(), boardLists);
    }

}
