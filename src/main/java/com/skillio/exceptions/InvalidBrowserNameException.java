package com.skillio.exceptions;

/**
 * This exception is thrown when an invalid browser name is provided to the openBrowser method.
 */

public class InvalidBrowserNameException extends RuntimeException {
	private String browserName;
	public  InvalidBrowserNameException(String browserName) {
		this.browserName=browserName;

	}
	@Override
	public String getMessage() {
		return this.browserName+" browser is not supported";
	}

}