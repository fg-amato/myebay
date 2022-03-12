package it.prova.myebay.exceptions;

public class InvalidAnnouncementException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public InvalidAnnouncementException(String message) {
		super(message);
	}
}
