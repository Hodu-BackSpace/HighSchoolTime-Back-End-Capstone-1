package hodubackspace.highschooltime.api.service;

import hodubackspace.highschooltime.api.advice.exception.access.NotAccessToAnotherCommentException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundBoardException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundCommentException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundMemberException;
import hodubackspace.highschooltime.api.controller.dto.request.RequestWriteComment;
import hodubackspace.highschooltime.api.repository.board.BoardRepository;
import hodubackspace.highschooltime.api.repository.comment.CommentRepository;
import hodubackspace.highschooltime.api.repository.member.MemberRepository;
import hodubackspace.highschooltime.domain.Board;
import hodubackspace.highschooltime.domain.Comment;
import hodubackspace.highschooltime.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void writeComment(Long writerId, Long boardId, RequestWriteComment requestDto) {
        Member findMember = memberRepository.findOne(writerId)
                .orElseThrow(() -> new NotFoundMemberException());

        Board findBoard = boardRepository.findOne(boardId)
                .orElseThrow(() -> new NotFoundBoardException());

        commentRepository.save(RequestWriteComment.toCommentEntity(findMember, findBoard, requestDto));
    }

    @Transactional
    public void deleteComment(Long memberId, Long commentId) {
        Comment findComment = commentRepository.findOneWithMember(commentId)
                .filter(comment -> comment.getMember().getId().equals(memberId))
                .orElseThrow(() -> new NotAccessToAnotherCommentException());

        findComment.deleteOwn(findComment);
    }
}
