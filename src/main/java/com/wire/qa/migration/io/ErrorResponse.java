package com.wire.qa.migration.io;

/**
 * A bean to map the error messages
 * 
 * @author bharat.gopalan
 *
 */
public class ErrorResponse {
	private final String message;

	public ErrorResponse(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
