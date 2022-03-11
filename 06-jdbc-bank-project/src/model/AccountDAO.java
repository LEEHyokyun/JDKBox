package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.login.AccountException;

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
		if(rs != null)
			rs.close();
		closeAll(pstmt, con);
	}
	
	public void createAccount(AccountVO accountVO) throws SQLException, CreateAccountException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//계좌개설시 입금액 0원에 대한 예외처리
		if(accountVO.getBalance() < 1000) {
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
			System.out.println(result+"개의 계좌정보가 개설되었습니다.");
		}finally {
			closeAll(pstmt, con);
		}
	}
}
