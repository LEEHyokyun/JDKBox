package model;

public class NoMoneyException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NoMoneyException(String message) {
		super(message);
	}
}
