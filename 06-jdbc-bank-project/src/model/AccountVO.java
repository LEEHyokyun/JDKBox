package model;

public class AccountVO {
	private String accountNo;
	private String name;
	private String password;
	private int balance;
	
	public AccountVO() {
		super();
	}
	
	//계좌생성 - 시퀀스가 있으므로 id는 별도 명기하지 않는다.
	public AccountVO(String name, String password, int balance) {
		super();
		this.name = name;
		this.password = password;
		this.balance = balance;
	}
	//조회시
	public AccountVO(String accountNo, String name, String password, int balance) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.password = password;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", name=" + name + ", password=" + password + ", balance="
				+ balance + "]";
	}
}
