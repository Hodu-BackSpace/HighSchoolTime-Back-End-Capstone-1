package hodubackspace.highschooltime.api.advice.exception.access;

public class NotAccessToOwnBoardException extends RuntimeException {
    public NotAccessToOwnBoardException() {
        super();
    }

    public NotAccessToOwnBoardException(String message) {
        super(message);
    }

    public NotAccessToOwnBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAccessToOwnBoardException(Throwable cause) {
        super(cause);
    }

    protected NotAccessToOwnBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
