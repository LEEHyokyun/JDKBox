package step2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.DbInfo;

public class TestJDBC2 {
	public static void main(String[] args) {
		try {
			Class.forName(DbInfo.DRIVER);
			System.out.println("db driver class loading");
			/*
			 * local DB or server DB에 저장가능!
			 */
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@59.11.246.222:1521:xe", DbInfo.USER, DbInfo.PASS);
			System.out.println("db Connected");
			//SQL : UPDATE
			String sql = "INSERT INTO MEMBER(ID, PASSWORD,	NAME, ADDRESS) VALUES(?,?,?,?)";
			/*
			 * ?을 변수로 하여, 특정 value값을 삽입할 수 있다.
			 */
			PreparedStatement pstmt =con.prepareStatement(sql);
			pstmt.setString(1, "helloworld"); //index 1 = attribute 1
			pstmt.setString(2, "1234");
			pstmt.setString(3, "hiworld");
			pstmt.setString(4, "용인");
			/*
			 * insert, delete, update -> executeUpdate() 권장, int return
			 * select -> executeQuery() 권장, ResultSet return
			 * *Query, 전체 table 조회에 유용
			 */
			int result = pstmt.executeUpdate(); //실행으로 영향을 받은 column 수	
			System.out.println("Insert count: "+result);
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
