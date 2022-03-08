package test.step1;

import java.sql.SQLException;
import java.util.Scanner;

import model.MemberDAO;
import model.MemberVO;

/*
 *  call -> MemberDAO -> DB
 *  call -> MemberVO -> main
 */
public class TestFindMemberById {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("검색할 회원의 아이디를 입력하세요: ");
		String id = scanner.nextLine();
		
		try {
			MemberDAO dao = new MemberDAO();
			MemberVO vo = dao.findMemberById(id);
			if(vo!=null) {
				System.out.println("검색결과: "+vo);
			}else {
				System.out.println(id+"에 대한 회원정보가 존재하지 않습니다.");
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
