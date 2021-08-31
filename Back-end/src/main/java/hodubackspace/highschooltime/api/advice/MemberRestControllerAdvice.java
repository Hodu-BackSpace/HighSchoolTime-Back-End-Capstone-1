package hodubackspace.highschooltime.api.advice;

import hodubackspace.highschooltime.api.advice.dto.ExceptionDto;
import hodubackspace.highschooltime.api.advice.exception.JoinDuplicateEmailException;
import hodubackspace.highschooltime.api.advice.exception.JoinDuplicateNickNameException;
import hodubackspace.highschooltime.api.advice.exception.LoginEmailNotFoundMemberException;
import hodubackspace.highschooltime.api.advice.exception.LoginPasswordNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@RestControllerAdvice
@Slf4j
public class MemberRestControllerAdvice {

    @ExceptionHandler(value = LoginEmailNotFoundMemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto loginNotFoundMemberException(LoginEmailNotFoundMemberException loginEmailNotFoundMemberException) {
        return new ExceptionDto("login", loginEmailNotFoundMemberException.getMessage());
    }

    @ExceptionHandler(value = LoginPasswordNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto loginPasswordNotMatchException(LoginPasswordNotMatchException loginPasswordNotMatchException) {
        return new ExceptionDto("login", loginPasswordNotMatchException.getMessage());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto badCredentialsException(BadCredentialsException badCredentialsException) {
        return new ExceptionDto("login", badCredentialsException.getMessage());
    }

    @ExceptionHandler(value = JoinDuplicateEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto joinDuplicateEmailException(JoinDuplicateEmailException joinDuplicateEmailException) {
        return new ExceptionDto("join", joinDuplicateEmailException.getMessage());
    }

    @ExceptionHandler(value = JoinDuplicateNickNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto joinDuplicateNickNameException(JoinDuplicateNickNameException joinDuplicateNickNameException) {
        return new ExceptionDto("join", joinDuplicateNickNameException.getMessage());
    }
}
