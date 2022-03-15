package test.step2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.ProductDAO;

public class TestProductGroupBy {
	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		
		ArrayList<HashMap<String, Object>> list;
		try {
			list = dao.findProductMakerGroupLessThanAvgPrice();
			for(int i=0;i<list.size();i++) {
				HashMap<String, Object> map = list.get(i);
				System.out.println(map.get("maker")+" "+map.get("avgprice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
