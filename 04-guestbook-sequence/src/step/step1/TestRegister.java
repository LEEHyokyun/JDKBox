package step.step1;

import java.sql.SQLException;
import java.util.Scanner;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestRegister {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("글제목을 입력하세요: ");
		String title = s.nextLine();
		System.out.println("본문내용을 입력하세요: ");
		String content = s.nextLine();
		s.close();
		
		GuestBookDAO dao = new GuestBookDAO();
		try {
			dao.registerBook(new GuestBookVO(title, content));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
