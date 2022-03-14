package test;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountNotFoundException;
import model.NotMatchedPasswordException;

//step2 계좌 잔액조회 테스트
public class TestUnit2 {
	public static void main(String[] args) {		
		
		try {
			AccountDAO dao = new AccountDAO();
			//1. AccountNotFoundException test : 존재하지 않는 계좌번호 입력 - "11 계좌번호에 해당하는 계좌가 존재하지 않습니다"
			//System.out.println("잔액조회:"+ dao.findBalanceByAccountNo("11", "1234"));
			
			//2.NotMatchedPasswordException test: 잘못된 비번입력  "계좌에 대한 패스워드가 일치하지 않습니다"
			//System.out.println("잔액조회:"+ dao.findBalanceByAccountNo("1", "1235"));
			
			//3.잔액조회 정상흐름 실행 		
			System.out.println("잔액조회:"+ dao.findBalanceByAccountNo("1", "1234"));
		}catch (AccountNotFoundException e) {
				System.out.println(e.getMessage());			
		}catch (NotMatchedPasswordException e) {			
			System.out.println(e.getMessage());			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
}






