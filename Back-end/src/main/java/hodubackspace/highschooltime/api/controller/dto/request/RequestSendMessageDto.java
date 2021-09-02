package hodubackspace.highschooltime.api.controller.dto.request;

import hodubackspace.highschooltime.domain.Member;
import hodubackspace.highschooltime.domain.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSendMessageDto {

    private Long toMemberId;
    private String content;

    public static Message toMessageEntity(Member fromMember, Long toMemberId, RequestSendMessageDto requestSendMessageDto) {
        return Message.createMessageEntity()
                .content(requestSendMessageDto.getContent())
                .fromMember(fromMember)
                .toMemberId(toMemberId)
                .build();
    }
}
