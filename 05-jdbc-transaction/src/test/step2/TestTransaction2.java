package test.step2;

import java.sql.SQLException;

import model.CardDAO;

/*
 * test2 : 카드db에 카드정보는 저장, 포인트db에는 포인트정보가 저장되지 않아 트랜잭션 처리오류
 * 이로 인한 rollback 발생
 */
public class TestTransaction2 {
	public static void main(String[] args) {
		CardDAO dao = new CardDAO();
		try {
			//dao.registerCardAndPoint("java", "아이유", "cgv", 10000);
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
