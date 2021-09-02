package hodubackspace.highschooltime.api.repository.message.query.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseSentMessageDto extends ResponseMessageDto{

    private Long toId;

    public ResponseSentMessageDto(Long messageId, Long toId, String content, LocalDateTime receivedDate) {
        super.setMessageId(messageId);
        this.toId = toId;
        super.setContent(content);
        super.setCreatedDate(receivedDate);
    }
}
