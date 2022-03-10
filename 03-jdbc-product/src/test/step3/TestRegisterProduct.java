package test.step3;

import java.sql.SQLException;

import model.ProductDAO;
import model.ProductVO;

public class TestRegisterProduct {
	public static void main(String[] args) {
		try {
			ProductDAO dao = new ProductDAO();
			ProductVO vo = new ProductVO(5, "갤럭시", "삼성", 1700);
			if (dao.findProudctById(vo.getId()) == null) {
				dao.registerProduct(vo);
				System.out.println("상품정보 등록완료: " + vo);
			} else
				System.out.println(vo.getId() + "에 해당하는 정보는 이미 존재합니다.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
