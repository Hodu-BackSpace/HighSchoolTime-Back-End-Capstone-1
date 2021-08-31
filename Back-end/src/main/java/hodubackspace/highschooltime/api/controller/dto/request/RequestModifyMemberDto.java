package hodubackspace.highschooltime.api.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestModifyMemberDto {
    private String nickName;
    private String password;
}
