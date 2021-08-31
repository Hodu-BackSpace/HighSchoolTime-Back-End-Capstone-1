package hodubackspace.highschooltime.api.advice.exception;

public class JoinDuplicateNickNameException extends RuntimeException {
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
