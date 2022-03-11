package test.step1;

import java.sql.SQLException;

import model.CardDAO;

/*
 * test1 : 카드db에 카드정보는 저장, 포인트db에는 포인트정보가 저장되지 않는 오류상황
 */
public class TestTransaction1 {
	public static void main(String[] args) {
		CardDAO dao = new CardDAO();
		try {
			//dao.registerCardAndPointVer1("java", "아이유", "cgv", 10000);
			/*
			 * card OK, point NG
			 * card DB에만 작업수행내역이 반영되고, point DB에는 반영되지 않는다.
			 */
			dao.registerCardAndPoint("spring", "박보검", null, 10000);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
