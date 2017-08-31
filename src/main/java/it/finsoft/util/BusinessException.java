package it.finsoft.util;

/**
 * Un'eccezione lanciata da un EJB.
 * 
 * @author Luca Vercelli
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 8303019007132170302L;

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
