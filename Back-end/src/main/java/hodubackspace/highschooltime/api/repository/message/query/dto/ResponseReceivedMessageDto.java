package hodubackspace.highschooltime.api.repository.message.query.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseReceivedMessageDto extends ResponseMessageDto{

    private Long fromId;

    public ResponseReceivedMessageDto(Long messageId,Long fromId, String content, LocalDateTime sentDate) {
        super.setMessageId(messageId);
        this.fromId = fromId;
        super.setContent(content);
        super.setCreatedDate(sentDate);
    }
}
