package io.gloe.gloe.exception;

public class GloeException extends Exception {
    public GloeException(String message) {
        super(message);
    }

    public GloeException(String message, Throwable cause) {
        super(message, cause);
    }

    public GloeException(Throwable cause) {
        super(cause);
    }
}
