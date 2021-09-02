package hodubackspace.highschooltime.api.controller;

import hodubackspace.highschooltime.api.common.annotation.TokenInfo;
import hodubackspace.highschooltime.api.controller.dto.ResponseResultDto;
import hodubackspace.highschooltime.api.controller.dto.request.RequestSendMessageDto;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseMessageDto;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseReceivedMessageDto;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseSentMessageDto;
import hodubackspace.highschooltime.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
@Slf4j
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseResultDto<String> sendMessage(@RequestBody @Valid RequestSendMessageDto request,
                                                 @TokenInfo Long memberId) {
        messageService.saveMessage(memberId, request.getToMemberId(), request);

        return new ResponseResultDto<>(0, "Success Send Message");
    }

    @GetMapping("/{messageId}")
    public ResponseResultDto<ResponseMessageDto> receivedMessage(@PathVariable("messageId") Long messageId) {
        return new ResponseResultDto<>(0, messageService.getReceiveMessage(messageId));
    }

    @GetMapping("/received")
    public ResponseResultDto<List<ResponseReceivedMessageDto>> receivedMessageList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                                   @TokenInfo Long memberId) {

        List<ResponseReceivedMessageDto> resultList = messageService.getReceiveMessageList(memberId, page);

        return new ResponseResultDto<>(resultList.size(), resultList);
    }

    @GetMapping("/sent")
    public ResponseResultDto<List<ResponseSentMessageDto>> sentMessageList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                           @TokenInfo Long memberId) {
        List<ResponseSentMessageDto> resultList = messageService.getSentMessageList(memberId, page);

        return new ResponseResultDto<>(resultList.size(), resultList);
    }
}
