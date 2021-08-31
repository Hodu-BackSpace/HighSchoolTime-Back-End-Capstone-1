package hodubackspace.highschooltime.api.controller;

import hodubackspace.highschooltime.api.common.annotation.TokenInfo;
import hodubackspace.highschooltime.api.controller.dto.request.RequestModifyMemberDto;
import hodubackspace.highschooltime.api.service.dto.response.ResponseMemberInfo;
import hodubackspace.highschooltime.api.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public Result<ResponseMemberInfo> infoMember(@TokenInfo Long loggedMemberId) {
        return new Result<ResponseMemberInfo>(1,memberService.memberInfo(loggedMemberId));
    }

    @PatchMapping("/members")
    public ResponseEntity<Result<String>> modifyMember(@TokenInfo Long loggedMemberId, @RequestBody @Valid RequestModifyMemberDto request) {
        memberService.updateMemberInfo(loggedMemberId,request);
        return ResponseEntity.status(HttpStatus.OK).body(new Result<>(1,"success"));

    }

    @Getter
    @AllArgsConstructor
    static class Result<T> {
        int count;
        T data;
    }

}
