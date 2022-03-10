package test.step2;

import java.sql.SQLException;
import java.util.Scanner;

import model.ProductDAO;
import model.ProductVO;

public class TestFindProductById {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("조회할 상품의 id를 입력하시오: ");
		int id = scanner.nextInt();
		scanner.close();
		try {
			ProductDAO dao = new ProductDAO();
			ProductVO vo = dao.findProudctById(id);
			if(vo != null)
				System.out.println("해당하는 상품정보는: "+vo);
			else
				System.out.println(id+"에 해당하는 상품정보가 없습니다.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
