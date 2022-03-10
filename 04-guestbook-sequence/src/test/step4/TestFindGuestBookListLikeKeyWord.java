package test.step4;

import java.util.ArrayList;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestFindGuestBookListLikeKeyWord {
	public static void main(String[] args) {
		GuestBookDAO dao = new GuestBookDAO();
		String keyword = "금";
		try{
			ArrayList<GuestBookVO> list = dao.findGuestBookListLikeKeyword(keyword);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
