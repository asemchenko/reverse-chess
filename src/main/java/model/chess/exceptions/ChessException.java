package model.chess.exceptions;

public class ChessException extends Exception {
    public ChessException() {
        super();
    }

    public ChessException(String message) {
        super(message);
    }

    public ChessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChessException(Throwable cause) {
        super(cause);
    }

    protected ChessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
