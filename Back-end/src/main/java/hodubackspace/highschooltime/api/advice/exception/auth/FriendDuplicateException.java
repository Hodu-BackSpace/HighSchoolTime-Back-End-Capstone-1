package hodubackspace.highschooltime.api.advice.exception.auth;

public class FriendDuplicateException extends DuplicateException {
    public FriendDuplicateException() {
        super();
    }

    public FriendDuplicateException(String message) {
        super(message);
    }

    public FriendDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FriendDuplicateException(Throwable cause) {
        super(cause);
    }

    protected FriendDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
