package hodubackspace.highschooltime.api.advice.exception;

public class NotFoundBoardGroupException extends RuntimeException{
    public NotFoundBoardGroupException() {
        super();
    }

    public NotFoundBoardGroupException(String message) {
        super(message);
    }

    public NotFoundBoardGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundBoardGroupException(Throwable cause) {
        super(cause);
    }

    protected NotFoundBoardGroupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
