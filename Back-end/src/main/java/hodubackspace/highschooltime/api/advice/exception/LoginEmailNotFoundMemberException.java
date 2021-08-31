package hodubackspace.highschooltime.api.advice.exception;

public class LoginEmailNotFoundMemberException extends RuntimeException {
    public LoginEmailNotFoundMemberException() {
        super();
    }

    public LoginEmailNotFoundMemberException(String message) {
        super(message);
    }

    public LoginEmailNotFoundMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginEmailNotFoundMemberException(Throwable cause) {
        super(cause);
    }

    protected LoginEmailNotFoundMemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
