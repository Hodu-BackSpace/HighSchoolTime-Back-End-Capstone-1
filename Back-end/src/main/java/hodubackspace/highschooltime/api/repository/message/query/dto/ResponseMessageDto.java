package hodubackspace.highschooltime.api.repository.message.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseMessageDto {

    private Long messageId;
    private Long fromMemberId;
    private String fromMemberName;
    private String content;
    private LocalDateTime createdDate;

    public ResponseMessageDto() {
    }
}
