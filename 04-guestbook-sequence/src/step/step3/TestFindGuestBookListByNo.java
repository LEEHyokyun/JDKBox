package step.step3;

import java.sql.SQLException;
import java.util.ArrayList;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestFindGuestBookListByNo {
	public static void main(String[] args) {
		GuestBookDAO dao = new GuestBookDAO();
		int startNo = 3;		int endNo = 4;
		try {
			ArrayList<GuestBookVO> list = dao.findGuestBookListByNo(startNo, endNo);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
