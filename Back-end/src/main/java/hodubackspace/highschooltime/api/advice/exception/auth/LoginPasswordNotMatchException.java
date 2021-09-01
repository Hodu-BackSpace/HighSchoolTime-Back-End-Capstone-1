package hodubackspace.highschooltime.api.advice.exception.auth;

public class LoginPasswordNotMatchException extends RuntimeException {
    public LoginPasswordNotMatchException() {
        super();
    }

    public LoginPasswordNotMatchException(String message) {
        super(message);
    }

    public LoginPasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginPasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected LoginPasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
