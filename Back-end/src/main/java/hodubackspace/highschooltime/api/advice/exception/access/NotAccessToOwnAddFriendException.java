package hodubackspace.highschooltime.api.advice.exception.access;

public class NotAccessToOwnAddFriendException extends NotAccessException {
    public NotAccessToOwnAddFriendException() {
        super();
    }

    public NotAccessToOwnAddFriendException(String message) {
        super(message);
    }

    public NotAccessToOwnAddFriendException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAccessToOwnAddFriendException(Throwable cause) {
        super(cause);
    }

    protected NotAccessToOwnAddFriendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
