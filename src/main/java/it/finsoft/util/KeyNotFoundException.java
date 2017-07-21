package it.finsoft.util;

public class KeyNotFoundException extends Exception {

	private static final long serialVersionUID = 8833332473151086867L;

	public KeyNotFoundException() {
	}

	public KeyNotFoundException(String msg) {
		super(msg);
	}
}
