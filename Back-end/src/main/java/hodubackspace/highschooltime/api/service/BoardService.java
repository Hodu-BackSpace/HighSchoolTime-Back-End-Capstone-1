package hodubackspace.highschooltime.api.service;

import hodubackspace.highschooltime.api.advice.exception.access.NotAccessToOwnBoardException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundBoardException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundBoardGroupException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundMemberException;
import hodubackspace.highschooltime.api.controller.dto.request.RequestWriteBoardDto;
import hodubackspace.highschooltime.api.repository.board.query.BoardQueryRepository;
import hodubackspace.highschooltime.api.repository.boardgroup.BoardGroupRepository;
import hodubackspace.highschooltime.api.repository.board.query.dto.ResponseBoardList;
import hodubackspace.highschooltime.api.repository.board.BoardRepository;
import hodubackspace.highschooltime.api.repository.member.MemberRepository;
import hodubackspace.highschooltime.api.service.dto.response.ResponseBoardInfo;
import hodubackspace.highschooltime.domain.Board;
import hodubackspace.highschooltime.domain.BoardGroup;
import hodubackspace.highschooltime.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final BoardGroupRepository boardGroupRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long writeBoard(String boardName, Long writerId, RequestWriteBoardDto requestWriteBoardDto) {
        BoardGroup boardGroup = boardGroupRepository.findOneByBoardName(boardName)
                .orElseThrow(() -> new NotFoundBoardGroupException());
        Member findMember = memberRepository.findOne(writerId)
                .orElseThrow(() -> new NotFoundMemberException());


        return boardRepository.saveReturnId(RequestWriteBoardDto.toBoardEntity(boardGroup, findMember, requestWriteBoardDto));
    }

    @Transactional
    public void increaseLikeCount(Long writerId, Long boardId) {
        Board findBoard = boardRepository.findOneWithMember(boardId)
                .orElseThrow(() -> new NotFoundBoardException());

        if (findBoard.getMember().getId().equals(writerId)) {
            throw new NotAccessToOwnBoardException();
        }

        findBoard.plusLikeCount();
    }

    public List<ResponseBoardList> viewBoardList(Long memberId, String boardName,int page) {
        Member findMember = memberRepository.findOne(memberId)
                .orElseThrow(() -> new NotFoundMemberException());

        return boardQueryRepository.findAllByBoardNameWithRegion(boardName, findMember.getSchoolInfo().getHighSchoolName(),page);
//        return boardRepository.findAllTest(boardName, findMember.getSchoolInfo().getHighSchoolName(), page)
//                .stream()
//                .map(ResponseBoardList::of)
//                .collect(Collectors.toList());
    }

    public ResponseBoardInfo viewBoardInfo(String boardName, Long boardId) {
        BoardGroup boardGroup = boardGroupRepository.findOneByBoardName(boardName)
                .orElseThrow(() -> new NotFoundBoardGroupException());

        Board findBoard = boardRepository.findOneWithCommentsMember(boardGroup,boardId)
                .orElseThrow(() -> new NotFoundBoardException());

        return ResponseBoardInfo.of(findBoard);
    }
}
