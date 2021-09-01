package hodubackspace.highschooltime.api.service;

import hodubackspace.highschooltime.api.advice.exception.auth.FriendDuplicateException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundFriendException;
import hodubackspace.highschooltime.api.advice.exception.found.NotFoundMemberException;
import hodubackspace.highschooltime.api.repository.friend.FriendRepository;
import hodubackspace.highschooltime.api.repository.member.MemberRepository;
import hodubackspace.highschooltime.api.service.dto.response.ResponseAddFriendInfoDto;
import hodubackspace.highschooltime.api.service.dto.response.ResponseFriendInfoDto;
import hodubackspace.highschooltime.domain.Friend;
import hodubackspace.highschooltime.domain.Member;
import hodubackspace.highschooltime.domain.status.FriendStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addFriend(Long fromId, Long toId, FriendStatus status, boolean duplicateCheck) {
        List<Member> members = memberRepository.findMultiple(fromId, toId);
        Member fromMember = members.stream().filter(member -> member.getId().equals(fromId))
                .findAny()
                .orElseThrow(() -> new NotFoundMemberException());

        // 중복 친구 추가 예외 검사
        if (duplicateCheck)
            DuplicateAddFriend(fromMember, toId);

        Member toMember = members.stream().filter(member -> member.getId().equals(toId))
                .findAny()
                .orElseThrow(() -> new NotFoundMemberException());

        Friend friendA = Friend.createFriendEntity()
                .friendMemberId(toId)
                .friendMemberName(toMember.getName())
                .friendStatus(status)
                .member(fromMember)
                .build();

        friendRepository.save(friendA);
    }

    private void DuplicateAddFriend(Member fromMember, Long toId) {
        if(fromMember.getFriends().stream()
                .filter(friend -> friend.getFriendMemberId().equals(toId))
                .findAny()
                .isPresent())
            throw new FriendDuplicateException();
    }

    public List<ResponseFriendInfoDto> beAddedFriendList(Long memberId) {
        return friendRepository.findBeAddedAllByMemberId(memberId).stream()
                .map(ResponseAddFriendInfoDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void applyFriend(Long memberId, Long friendId) {
        // 1. 친구 엔티티 생성
        addFriend(memberId, friendId, FriendStatus.COMPLETE,false);

        // 2. 기존 친구 상태 완료로 바꾼다.
        Friend myFriend = friendRepository.findOneWithMemberIdFriendId(friendId, memberId)
                .orElseThrow(() -> new NotFoundFriendException());

        myFriend.updateStatus(FriendStatus.COMPLETE);
    }

    public List<ResponseFriendInfoDto> friendList(Long memberId) {
        return friendRepository.findAllByMemberId(memberId)
                .stream()
                .map(ResponseFriendInfoDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeFriend(Long memberId, Long friendId) {
        friendRepository.removes(memberId, friendId);
    }

}
