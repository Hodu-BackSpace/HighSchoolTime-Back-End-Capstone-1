package hodubackspace.highschooltime.api.controller;

import hodubackspace.highschooltime.api.advice.exception.access.NotAccessToOwnAddFriendException;
import hodubackspace.highschooltime.api.common.annotation.TokenInfo;
import hodubackspace.highschooltime.api.controller.dto.ResponseResultDto;
import hodubackspace.highschooltime.api.service.FriendService;
import hodubackspace.highschooltime.api.service.dto.response.ResponseFriendInfoDto;
import hodubackspace.highschooltime.domain.status.FriendStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
@Slf4j
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/{friendId}")
    public ResponseResultDto<String> addingFriend(@TokenInfo Long fromId,
                                                  @PathVariable("friendId") Long toId) {
        if (fromId.equals(toId)) {
            throw new NotAccessToOwnAddFriendException();
        }

        friendService.addFriend(fromId, toId, FriendStatus.WAIT,true);

        return new ResponseResultDto<>(0, "Success Add Friend");
    }

    @GetMapping("/apply")
    public ResponseResultDto<List<ResponseFriendInfoDto>> getBeAddedFriendList(@TokenInfo Long memberId) {
        List<ResponseFriendInfoDto> resultData = friendService.beAddedFriendList(memberId);
        return new ResponseResultDto<>(resultData.size(), resultData);
    }

    @PostMapping("/{friendId}/apply")
    public ResponseResultDto<String> applyingFriend(@TokenInfo Long memberId,
                                                    @PathVariable("friendId") Long friendId) {
        friendService.applyFriend(memberId, friendId);
        return new ResponseResultDto<>(0, "Success Apply Friend");
    }

    @GetMapping
    public ResponseResultDto<List<ResponseFriendInfoDto>> getFriendList(@TokenInfo Long memberId) {

        List<ResponseFriendInfoDto> resultData = friendService.friendList(memberId);
        return new ResponseResultDto<>(resultData.size(), resultData);
    }

    @DeleteMapping("/{friendId}")
    public ResponseResultDto<String> deleteFriend(@TokenInfo Long memberId,
                                                  @PathVariable("friendId") Long friedId) {
        friendService.removeFriend(memberId, friedId);

        return new ResponseResultDto<>(0, "Success Delete Friend");
    }
}
