package hodubackspace.highschooltime.api.service.dto.response;

import hodubackspace.highschooltime.domain.Friend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFriendInfoDto {
    private Long friendMemberId;
    private String friendMemberName;

    public static ResponseFriendInfoDto of(Friend friend) {
        return new ResponseFriendInfoDto(friend.getFriendMemberId(), friend.getFriendMemberName());
    }
}
