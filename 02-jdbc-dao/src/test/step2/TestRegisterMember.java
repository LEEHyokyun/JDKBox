package test.step2;

import java.sql.SQLException;

import model.MemberDAO;
import model.MemberVO;

public class TestRegisterMember {
	public static void main(String[] args) {
		try {
			MemberDAO dao = new MemberDAO();
			MemberVO vo = new MemberVO("html", "a", "이효리", "애월읍");
			/*
			 * 일단 VO객체에 생성자 초기화
			 */
			if(dao.findMemberById(vo.getId()) == null){
				dao.registerMember(vo);
				System.out.println(vo+ "회원 등록 완료되었습니다."); //toString
			}else {
				System.out.println(vo.getId()+"회원 정보가 존재하여 중복 등록할 수 없습니다.");
			}
			
		}catch(ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
			e.printStackTrace();
		}
 }
}
