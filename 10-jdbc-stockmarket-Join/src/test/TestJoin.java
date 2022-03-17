package test;

import java.util.ArrayList;

import model.SharesVO;
import model.StockMarketDAO;

public class TestJoin {
	public static void main(String[] args) {
		StockMarketDAO dao = new StockMarketDAO();
		String id = "java";
		try {
			ArrayList<SharesVO> list = dao.findSharesListById(id);
			for(SharesVO vo:list) {
				System.out.println(vo.getName()+"님께서 "+vo.getSymbol()+"의 주식을"+vo.getQuantity()+"만큼 보유하여 "+vo.getTotalPrice()+"의 총 주식액을 보유하고 있습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
