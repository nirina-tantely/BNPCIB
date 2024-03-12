package org.bnpcib.exception;

public class FunctionalException extends Exception {

    public FunctionalException() {
        super();
    }

    public FunctionalException(final String message) {
        super(message);
    }

    public FunctionalException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FunctionalException(final Throwable cause) {
        super(cause);
    }

    public static FunctionalException wrap(final Throwable t) {
        if (t instanceof FunctionalException) {
            return (FunctionalException) t;
        } else {
            return new FunctionalException(t);
        }
    }
}
