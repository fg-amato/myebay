package it.prova.myebay.exceptions;

public class InvalidCreditException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCreditException(String message) {
		super(message);
	}
}
