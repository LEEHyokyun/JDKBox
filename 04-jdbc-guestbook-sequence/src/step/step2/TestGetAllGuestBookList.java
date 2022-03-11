package step.step2;

import java.sql.SQLException;
import java.util.ArrayList;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestGetAllGuestBookList {
	public static void main(String[] args) {
		GuestBookDAO dao = new GuestBookDAO();
		try {
			ArrayList<GuestBookVO> list = dao.getAllGuestBookList();
			//최종 출력
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
