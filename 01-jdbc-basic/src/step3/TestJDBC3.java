package step3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import common.DbInfo;

/*
 * SELECT를 이용한 회원 검색
 */
public class TestJDBC3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("조회할 ID를 입력하세요: ");
		String ID = scanner.nextLine();
		System.out.println(ID+" 회원정보 조회는 아래와 같습니다.");
		
		try {
			Class.forName(DbInfo.DRIVER);
			Connection con = DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
			String sql = "SELECT PASSWORD, NAME, ADDRESS FROM member WHERE ID=?";
			//? = 변수처리
			PreparedStatement pstmt = con.prepareStatement(sql);
			//? 변수에 ID를 할당 - ***"변수에 값 할당 = setString"***
			//? 1개 = index 1, 그곳에 값 할당
			pstmt.setString(1, ID); 
			ResultSet rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				//id는 유일식별자, 존재할 경우 true 없다면 false
				System.out.println("password: "+rs.getString(1));
				System.out.println("NAME: "+rs.getString(2));
				System.out.println("ADDRESS: "+rs.getString(3));
				/*
				 * SELECT PASSWORD .. = index 1
				 */
			} else {
				System.out.println("해당 회원정보가 존재하지 않습니다.");
			}
			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scanner.close();
	}
}
