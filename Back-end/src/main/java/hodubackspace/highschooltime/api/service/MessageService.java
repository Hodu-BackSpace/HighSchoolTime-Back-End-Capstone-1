package hodubackspace.highschooltime.api.service;

import hodubackspace.highschooltime.api.advice.exception.found.NotFoundException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundMemberException;
import hodubackspace.highschooltime.api.controller.dto.request.RequestSendMessageDto;
import hodubackspace.highschooltime.api.repository.member.MemberRepository;
import hodubackspace.highschooltime.api.repository.message.MessageRepository;
import hodubackspace.highschooltime.api.repository.message.query.MessageQueryRepository;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseMessageDto;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseReceivedMessageDto;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseSentMessageDto;
import hodubackspace.highschooltime.domain.Member;
import hodubackspace.highschooltime.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageQueryRepository messageQueryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveMessage(Long fromMemberId, Long toMemberId, RequestSendMessageDto requestDto) {
        Member fromMember = memberRepository.findOne(fromMemberId)
                .orElseThrow(() -> new NotFoundMemberException());

        Message message = RequestSendMessageDto.toMessageEntity(fromMember, toMemberId, requestDto);

        messageRepository.save(message);
    }

    public ResponseMessageDto getReceiveMessage(Long messageId) {
        return messageQueryRepository.findOneReceiveMessage(messageId)
                .orElseThrow(() -> new NotFoundException());
    }

    public List<ResponseReceivedMessageDto> getReceiveMessageList(Long memberId, int page) {
        return messageQueryRepository.findAllReceivedMessage(memberId, page);
    }

    public List<ResponseSentMessageDto> getSentMessageList(Long memberId, int page) {
        return messageQueryRepository.findAllSentMessage(memberId, page);
    }

}
