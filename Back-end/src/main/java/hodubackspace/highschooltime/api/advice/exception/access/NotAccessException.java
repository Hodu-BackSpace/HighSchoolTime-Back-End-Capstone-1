package hodubackspace.highschooltime.api.advice.exception.access;

public class NotAccessException extends RuntimeException{
    public NotAccessException() {
        super();
    }

    public NotAccessException(String message) {
        super(message);
    }

    public NotAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAccessException(Throwable cause) {
        super(cause);
    }

    protected NotAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
