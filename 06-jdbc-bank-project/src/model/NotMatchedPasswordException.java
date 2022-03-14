package model;

public class NotMatchedPasswordException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NotMatchedPasswordException(String message) {
		super(message);
	}
}
