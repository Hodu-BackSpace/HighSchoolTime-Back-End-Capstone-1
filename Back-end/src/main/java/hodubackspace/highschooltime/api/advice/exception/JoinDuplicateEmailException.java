package hodubackspace.highschooltime.api.advice.exception;

public class JoinDuplicateEmailException extends RuntimeException {
    public JoinDuplicateEmailException() {
        super();
    }

    public JoinDuplicateEmailException(String message) {
        super(message);
    }

    public JoinDuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public JoinDuplicateEmailException(Throwable cause) {
        super(cause);
    }

    protected JoinDuplicateEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
