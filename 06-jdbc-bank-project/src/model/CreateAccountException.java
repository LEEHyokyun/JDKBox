package model;

public class CreateAccountException extends Exception {
	private static final long serialVersionUID = 1L;

	/*
	 * 계좌개설시 발생할 수 있는 Exception
	 * 초기 납입액이 1000원 미만일 경우 발생
	 */
	public CreateAccountException(String message) {
		super(message);
	}
}
