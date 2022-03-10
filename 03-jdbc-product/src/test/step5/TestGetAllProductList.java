package test.step5;

import java.sql.SQLException;
import java.util.ArrayList;

import model.ProductDAO;
import model.ProductVO;

public class TestGetAllProductList {
	public static void main(String[] args) {
		try {
			ProductDAO dao = new ProductDAO();
			//상품 리스트를 반환받아 내림차순으로 
			ArrayList<ProductVO> list = dao.getAllproductList();
			for(int i=0;i<list.size();i++)
				System.out.println(list.get(i));
			}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
