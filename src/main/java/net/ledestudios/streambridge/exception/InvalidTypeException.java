package net.ledestudios.streambridge.exception;

public class InvalidTypeException extends RuntimeException {

    public InvalidTypeException() {
    }

    public InvalidTypeException(String message) {
        super(message);
    }

    public InvalidTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTypeException(Throwable cause) {
        super(cause);
    }

}
