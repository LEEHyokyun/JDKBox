package test.step1;

import java.sql.SQLException;

import model.ProductDAO;

public class TestTotalCount {
	public static void main(String[] args) {
		try {
			ProductDAO dao = new ProductDAO();
			int totalCount = dao.getProductTotalCount();
			System.out.println("총 상품수 = "+totalCount);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
