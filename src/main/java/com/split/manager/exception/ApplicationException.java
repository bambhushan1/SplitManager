package com.split.manager.exception;

import com.split.manager.util.ApplicationCode;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 4265758284484258031L;

	public static final String UNHANDLED_EXCEPTION_TXT = "Unhandled Exception !!! ";
	
	private String message;
	private boolean logged;
	private  ApplicationCode errorCode;
	
	public ApplicationException(final ApplicationCode code) {
		this.errorCode = code;
	}
	
	public ApplicationException(final String message, final ApplicationCode code) {
		this.setMessage(message);
		this.errorCode = code;
	}

	public ApplicationException(final String message, final ApplicationCode code, final Throwable throwable) {
		super(throwable);
		this.errorCode = code;
		this.setMessage(message);
	}
	
	public ApplicationCode getErrorCode() {
		return errorCode;
	}

	public boolean isLogged() {
		return this.logged;
	}
	
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
