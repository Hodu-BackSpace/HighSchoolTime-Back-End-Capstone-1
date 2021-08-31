package hodubackspace.highschooltime.api.service;

import hodubackspace.highschooltime.api.advice.exception.NotFoundBoardException;
import hodubackspace.highschooltime.api.advice.exception.NotFoundBoardGroupException;
import hodubackspace.highschooltime.api.advice.exception.NotFoundMemberException;
import hodubackspace.highschooltime.api.controller.dto.request.RequestWriteBoardDto;
import hodubackspace.highschooltime.api.repository.board.query.BoardQueryRepository;
import hodubackspace.highschooltime.api.repository.boardgroup.BoardGroupRepository;
import hodubackspace.highschooltime.api.repository.dto.ResponseBoardList;
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
import java.util.Optional;

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

    public List<ResponseBoardList> viewBoardList(Long memberId, String boardName,int page) {
        Member findMember = memberRepository.findOne(memberId)
                .orElseThrow(() -> new NotFoundMemberException());

        return boardQueryRepository.findAllByBoardNameWithRegion(boardName, findMember.getSchoolInfo().getHighSchoolName(),page);
    }

    public ResponseBoardInfo viewBoardInfo(String boardName, Long boardId) {
        BoardGroup boardGroup = boardGroupRepository.findOneByBoardName(boardName)
                .orElseThrow(() -> new NotFoundBoardGroupException());

        Board findBoard = boardRepository.findOneWithComments(boardGroup,boardId)
                .orElseThrow(() -> new NotFoundBoardException());

        return ResponseBoardInfo.of(findBoard);
    }
}
