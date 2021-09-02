package hodubackspace.highschooltime.api.advice.exception.access;

public class NotAccessToAnotherCommentException extends NotAccessException {
    public NotAccessToAnotherCommentException() {
        super();
    }

    public NotAccessToAnotherCommentException(String message) {
        super(message);
    }

    public NotAccessToAnotherCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAccessToAnotherCommentException(Throwable cause) {
        super(cause);
    }

    protected NotAccessToAnotherCommentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
