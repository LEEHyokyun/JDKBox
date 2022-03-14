package test;

import java.sql.SQLException;
import java.util.ArrayList;

import model.AccountDAO;
import model.AccountVO;

//step8 최고 잔액을 가진 계좌 리스트를 조회 ( subquery ) 
public class TestUnit8 {
	public static void main(String[] args) {
		
		try {
			AccountDAO dao=new AccountDAO();
			ArrayList<AccountVO> list=dao.findHighestBalanceAccount();
			for(AccountVO vo:list)
				System.out.println("VIP고객의 계좌정보입니다: "+vo);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
