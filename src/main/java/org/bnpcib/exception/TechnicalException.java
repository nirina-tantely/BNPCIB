package org.bnpcib.exception;

public class TechnicalException extends RuntimeException {

	public TechnicalException() {
		super();
	}

	public TechnicalException(final String message) {
		super(message);
	}

	public TechnicalException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TechnicalException(final Throwable cause) {
		super(cause);
	}

}
