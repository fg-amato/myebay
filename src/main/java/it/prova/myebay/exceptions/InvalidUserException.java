package it.prova.myebay.exceptions;

public class InvalidUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidUserException(String message) {
		super(message);
	}
}
