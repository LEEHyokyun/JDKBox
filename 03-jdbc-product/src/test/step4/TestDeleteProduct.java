package test.step4;

import java.sql.SQLException;

import model.ProductDAO;

public class TestDeleteProduct {
	public static void main(String[] args) {
		try {
			ProductDAO dao = new ProductDAO();
			int id = 5;
			if(dao.findProudctById(id)==null) {
				System.out.println(id+"에 해당하는 정보가 없어서 삭제가 불가능합니다.");
			}else {
				dao.deleteProductById(id);
				System.out.println(id+"에 해당하는 상품정보를 삭제하였습니다.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
