package hodubackspace.highschooltime.api.service;

import hodubackspace.highschooltime.api.controller.dto.request.RequestModifyMemberDto;
import hodubackspace.highschooltime.api.service.dto.response.ResponseMemberInfo;
import hodubackspace.highschooltime.api.repository.member.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseMemberInfo memberInfo(Long loggedMemberId) {
        return memberRepository.findOne(loggedMemberId)
                .map(ResponseMemberInfo::of)
                .orElseThrow(() -> new RuntimeException("Runtime Exception!"));
    }

    @Transactional
    public void updateMemberInfo(Long loggedMemberId, RequestModifyMemberDto requestDto) {
        memberRepository.findOne(loggedMemberId)
                .ifPresentOrElse(member -> member.updatePasswordNickNameOptional(requestDto),
                        () -> new RuntimeException());
    }

    @Getter
    @AllArgsConstructor
    static class Result<T> {
        int count;
        T data;
    }
}
