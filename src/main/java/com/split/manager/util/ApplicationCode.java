package com.split.manager.util;

import java.util.NoSuchElementException;

/**
 * Set of Application Codes that are used for indicating specific error scenarios
 * 
 * @author Bhushan Mahajan
 *
 */
public enum ApplicationCode {

	STATUS_OK("response.ok"),
	INVALID_INPUT("response.input.invalid"),
	USER_NOT_FOUND("response.user.not.found"),
	BILL_NOT_FOUND("response.bill.not.found"),
	BASE_DB_ERROR("response.db.connection_fail"), 
	UNEXPECTED_ERROR("response.internal_server_error"), 
	USER_ALREADY_EXIST("response.duplicate.email"), 
	DATA_VALIDATION_ERROR("response.invalid_input_data"),
	MISSING_EMAIL("response.missing.email");
	
	private final String codeId;

	private ApplicationCode(final String codeId) {
		this.codeId = codeId;
	}

	public String getCodeId() {
		return this.codeId;
	}

	/**
	 * Converts an int value into an ErrorCode
	 * 
	 * @param errorCode
	 * @return {@link ApplicationCode}
	 */
	public static ApplicationCode getExceptionCode(final int errorCode) {

		ApplicationCode eErrorCode = null;
		for (final ApplicationCode status : ApplicationCode.values()) {
			if (status.getCodeId().equals(errorCode)){
				eErrorCode = status;
				break;
			}
		}
		if (null == eErrorCode) {
			throw new NoSuchElementException("The received code " + errorCode + " is not valid !!!");
		} else {
			return eErrorCode;
		}
	}
}
