package hodubackspace.highschooltime.api.advice.exception.auth;

public class JoinDuplicateNickNameException extends DuplicateException {
    public JoinDuplicateNickNameException() {
        super();
    }

    public JoinDuplicateNickNameException(String message) {
        super(message);
    }

    public JoinDuplicateNickNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public JoinDuplicateNickNameException(Throwable cause) {
        super(cause);
    }

    protected JoinDuplicateNickNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
