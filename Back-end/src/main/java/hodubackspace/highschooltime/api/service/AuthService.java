package hodubackspace.highschooltime.api.service;

import hodubackspace.highschooltime.api.advice.exception.auth.JoinDuplicateEmailException;
import hodubackspace.highschooltime.api.advice.exception.auth.JoinDuplicateNickNameException;
import hodubackspace.highschooltime.api.advice.exception.auth.LoginEmailNotFoundMemberException;
import hodubackspace.highschooltime.api.common.security.auth.ResponseTokenDto;
import hodubackspace.highschooltime.api.common.security.auth.TokenProvider;
import hodubackspace.highschooltime.api.controller.dto.request.RequestJoinMemberDto;
import hodubackspace.highschooltime.api.controller.dto.request.RequestLoginMemberDto;
import hodubackspace.highschooltime.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
//    private final MemberTestRepository memberTestRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(RequestJoinMemberDto requestDto){
        validateDuplicateMember(requestDto);
        requestDto.encodePassword(passwordEncoder.encode(requestDto.getPassword()));
        memberRepository.save(RequestJoinMemberDto.toMemberEntity(requestDto));
    }

    public void joinCheckByNickName(String nickName) {
        try {
            memberRepository.findOneByNickName(nickName);
        } catch (EmptyResultDataAccessException e) {
            return;
        }
        throw new JoinDuplicateNickNameException("Already use Nickname");
    }

    public void joinCheckByEmail(String email) {
        try {
            memberRepository.findOneByEmail(email);
        } catch (LoginEmailNotFoundMemberException e){
            return;
        }
        throw new JoinDuplicateEmailException("Already use Email");

    }

    private void validateDuplicateMember(RequestJoinMemberDto requestDto) {
        if (!memberRepository.findAllByEmail(requestDto.getEmail()).isEmpty()) {
            throw new JoinDuplicateEmailException("Duplicate Member!");
        }
    }

    public ResponseTokenDto login(RequestLoginMemberDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        ResponseTokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        return tokenDto;

    }
}
