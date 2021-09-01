package hodubackspace.highschooltime.api.advice.exception.found;

public class NotFoundFriendException extends RuntimeException {
    public NotFoundFriendException() {
        super();
    }

    public NotFoundFriendException(String message) {
        super(message);
    }

    public NotFoundFriendException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundFriendException(Throwable cause) {
        super(cause);
    }

    protected NotFoundFriendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
