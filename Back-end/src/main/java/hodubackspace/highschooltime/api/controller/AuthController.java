package hodubackspace.highschooltime.api.controller;

import hodubackspace.highschooltime.api.common.security.auth.ResponseTokenDto;
import hodubackspace.highschooltime.api.controller.dto.request.RequestJoinMemberDto;
import hodubackspace.highschooltime.api.controller.dto.request.RequestLoginMemberDto;
import hodubackspace.highschooltime.api.service.AuthService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<MemberController.Result<String>> joinMember(@RequestBody @Valid RequestJoinMemberDto request) {
        authService.join(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberController.Result<>(1,"success created"));
    }

    @PostMapping("/join/nickname")
    public ResponseEntity<MemberController.Result<String>> joinMemberCheckByNickName(@RequestParam("nickname") String nickName) {
        authService.joinCheckByNickName(nickName);
        return ResponseEntity.status(HttpStatus.OK).body(new MemberController.Result<>(1, "possible nickName"));
    }

    @PostMapping("/join/email")
    public ResponseEntity<MemberController.Result<String>> joinMemberCheckByEmail(@RequestParam("email") String email) {
        authService.joinCheckByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(new MemberController.Result<>(1, "possible email"));
    }

    @PostMapping("/login")
    public ResponseEntity<MemberController.Result<ResponseTokenDto>> loginMember(@RequestBody @Valid RequestLoginMemberDto request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MemberController.Result<>(1, authService.login(request)));
    }
}
