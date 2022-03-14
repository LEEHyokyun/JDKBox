package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbInfo;

public class AccountDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}

	/**
	 * 계좌개설 납입액 최소금액 1000원에 대해 예외처리
	 * 
	 * @param accountVO
	 * @throws SQLException
	 * @throws CreateAccountException
	 */
	public void createAccount(AccountVO accountVO) throws SQLException, CreateAccountException {
		Connection con = null;
		PreparedStatement pstmt = null;

		// 계좌개설시 입금액 0원에 대한 예외처리
		if (accountVO.getBalance() < 1000) {
			throw new CreateAccountException("계좌 개설시 최소 1000원이상의 금액이 입금되어야 합니다.");
		}

		try {
			con = getConnection();
			String sql = "INSERT INTO account(ACCOUNT_NO, NAME, PASSWORD, BALANCE) VALUES(account_seq.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountVO.getName());
			pstmt.setString(2, accountVO.getPassword());
			pstmt.setInt(3, accountVO.getBalance());
			int result = pstmt.executeUpdate();
			System.out.println(result + "개의 계좌정보가 개설되었습니다.");
		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 계좌잔액조회 계좌번호에 해당하는 계좌가 없거나(AccountNotFound), 비밀번호가 일치하지 않을
	 * 경우(NotMatchedPassword) 예외처리 계좌가 존재, 비밀번호 일치할 경우 잔액을 반환
	 * 
	 * @param accountNo
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 */
	public int findBalanceByAccountNo(String accountNo, String password)
			throws SQLException, AccountNotFoundException, NotMatchedPasswordException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int balance = 0;

			con = getConnection();
			String sql = "SELECT BALANCE , PASSWORD FROM account WHERE ACCOUNT_NO= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (!rs.getString(2).equals(password)) {
					throw new NotMatchedPasswordException("올바른 비밀번호가 아닙니다.");
				}
				balance = rs.getInt(1);
			} else {
				throw new AccountNotFoundException(accountNo + "에 해당하는 계좌가 존재하지 않습니다.");
			}

			return balance;

		} finally {
			closeAll(rs, pstmt, con);
		}
	}

	/**
	 * 계좌번호 유무 및 계좌번호에 따른 비밀번호 일치여부 확인 <br>
	 * 계좌번호가 존재하지 않는다면 AccountNotFoundException <br>
	 * 패스워드가 일치하지 않는다면 NotMatchedPasswordException <br>
	 * 
	 * @param accountNo
	 * @param password
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 */
	public void checkAccountNoAndPassword(String accountNo, String password)
			throws SQLException, AccountNotFoundException, NotMatchedPasswordException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "SELECT ACCOUNT_NO, NAME, PASSWORD, BALANCE FROM account WHERE ACCOUNT_NO = ? AND PASSWORD = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (!password.equals(rs.getString(3))) {
					throw new NotMatchedPasswordException("비밀번호가 일치하지 않습니다.");
				}
				System.out.println(new AccountVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			} else {
				throw new AccountNotFoundException(accountNo + "에 해당하는 계좌정보가 존재하지 않습니다.");
			}

		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 계좌입금 <br>
	 * 계좌번호가 존재하지 않거나, 비밀번호가 일치하지 않는다면 Exception처리 <br>
	 * 입금액이 0원 이하일 경우 Exception처리 <br>
	 * 위 예외상황이 발생하지 않을 경우 정상처리
	 * 
	 * @param accountNo
	 * @param password
	 * @param money
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 * @throws NoMoneyException
	 * @throws InsufficientBalanceException
	 */
	public void deposit(String accountNo, String password, int money)
			throws SQLException, AccountNotFoundException, NotMatchedPasswordException, NoMoneyException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			/*
			 * 계좌번호 및 비밀번호 정보 예외처리 및 출금액/잔액에 대한 예외처리 필요
			 */
			checkAccountNoAndPassword(accountNo, password);
			if (money == 0) {
				throw new NoMoneyException("입금액은 0원이 될 수 없습니다.");
			}

			con = getConnection();
			String sql = "UPDATE account SET balance = balance + ? WHERE ACCOUNT_NO = ? AND PASSWORD = ?";
			/*
			 * account_no가 유일자이기 때문에 굳이 password를 조건절에 넣지 않아도 된다.
			 */
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, accountNo);
			pstmt.setString(3, password);

			int result = pstmt.executeUpdate();
			System.out.println(result + "columns have been updated");

		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 계좌출금 출금액이 0원 이하일 경우 NoMoneyException 계좌번호에 해당하는 계좌가 없거나 비밀번호가 일치하지 않을 경우
	 * AccountNotFoundException, NotMatchedPasswordException 잔액이 부족할 경우
	 * InsufficientException
	 * 
	 * @param accountNo
	 * @param password
	 * @param money
	 * @throws SQLException
	 * @throws NoMoneyException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 * @throws InsufficientBalanceException
	 */
	public void withdraw(String accountNo, String password, int money) throws SQLException, NoMoneyException,
			AccountNotFoundException, NotMatchedPasswordException, InsufficientBalanceException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int balance = findBalanceByAccountNo(accountNo, password);

		try {
			checkAccountNoAndPassword(accountNo, password);
			if (money == 0) {
				throw new NoMoneyException("출금액은 0원이 될 수 없습니다.");
			}
			if (balance - money < 0) {
				throw new InsufficientBalanceException("출금액이 잔액보다 클 수 없습니다.");
			}

			con = getConnection();
			String sql = "UPDATE account SET balance = balance - ? WHERE ACCOUNT_NO = ?";
			/*
			 * account_no가 유일자이므로, 굳이 password까지 조건절에 명기할 필요는 없다.
			 */
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, accountNo);
			int result = pstmt.executeUpdate();
			System.out.println(result + " columns have been updated");

		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 계좌번호에 해당하는 계좌 존재여부 확인
	 * 
	 * @param accountNo
	 * @return
	 * @throws SQLException
	 */
	public boolean existsAccountNo(String accountNo) throws SQLException {
		boolean isExist = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "SELECT COUNT(*) FROM account WHERE ACCOUNT_NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();

			if (rs.next() && rs.getInt(1) == 1) {
				/*
				 * count = 1 -> 존재한다는 의미, account no는 유일자이므로 사실 rs.next()만 명기해주어도 된다.
				 */
				// System.out.println("존재합니다.");
				isExist = true;
			}
			return isExist;

		} finally {
			closeAll(rs, pstmt, con);
		}
	}

	/**
	 * 계좌이체 송금자와 수금자의 조건이 모두 누락되지 않을 경우에만 진행
	 * 
	 * @param senderAccountNo
	 * @param password
	 * @param money
	 * @param receiverAccountNo
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 * @throws NoMoneyException
	 * @throws InsufficientBalanceException
	 */
	public void transfer(String senderAccountNo, String password, int money, String receiverAccountNo)
			throws SQLException, AccountNotFoundException, NotMatchedPasswordException, NoMoneyException,
			InsufficientBalanceException {
		
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(receiverAccountNo);
		int senderBalance = findBalanceByAccountNo(senderAccountNo, password);
		
		try {
			/*
			 * exception tests
			 */
			// checkAccountNoAndPassword(senderAccountNo, password);
			if (money == 0) { // 입금액 확인
				throw new NoMoneyException("송금금액은 0원 초과이어야 합니다.");
			}
			
			
			if (senderBalance < money) { // 송금자 잔액 확인
				//System.out.println("Exception 처리 진행");
				throw new InsufficientBalanceException("출금액이 잔액보다 클 수 없습니다.");
			}
			
			if (!existsAccountNo(receiverAccountNo)) { // 수신자 계좌정보 확인
				throw new AccountNotFoundException(receiverAccountNo + "에 해당하는 계좌정보가 존재하지 않습니다.");
			}
			

			
			con = getConnection();
			con.setAutoCommit(false);
			/*
			 * passive commit, set transaction process
			 */
			String senderSql = "UPDATE account SET BALANCE = BALANCE - ? WHERE ACCOUNT_NO = ?";
			pstmt = con.prepareStatement(senderSql);
			pstmt.setInt(1, money);
			pstmt.setString(2, senderAccountNo);
			pstmt.executeUpdate();
			pstmt.close();
			/*
			 * SQL 두번 이상 실행될 경우, pstmt stream close 먼저 실행 후 진행
			 */

			String receiverSql = "UPDATE account SET BALANCE = BALANCE + ? WHERE ACCOUNT_NO = ?";
			pstmt = con.prepareStatement(receiverSql);
			pstmt.setInt(1, money);
			pstmt.setString(2, receiverAccountNo);
			pstmt.executeUpdate();

			con.commit();
			System.out.println("계좌이체가 정상적으로 완료되었습니다.");
		} catch (Exception e) {
			con.rollback();
			/*
			 * exception 전달
			 */
			throw e;
		} finally {
			closeAll(pstmt, con);
		}
	}

	public ArrayList<AccountVO> findHighestBalanceAccount() throws SQLException {
		// AccountNo, Name, Balance
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			String sql = "SELECT ACCOUNT_NO, NAME, PASSWORD, BALANCE FROM account WHERE BALANCE = (SELECT MAX(BALANCE) FROM account)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new AccountVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}

		return list;
	}
}
