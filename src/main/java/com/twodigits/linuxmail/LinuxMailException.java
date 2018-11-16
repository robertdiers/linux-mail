package com.twodigits.linuxmail;

/**
 * wrapper mail send exception
 * @author robert.diers
 */
public class LinuxMailException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message = "";
	
	public LinuxMailException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
