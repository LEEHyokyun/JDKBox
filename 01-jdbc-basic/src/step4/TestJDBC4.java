package step4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.DbInfo;

/*
 * DELETE
 */
public class TestJDBC4 {
	public static void main(String[] args) {
		try {
			Class.forName(DbInfo.DRIVER);
			Connection con = DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
			System.out.println("DB Connected");
			String sql = "DELETE FROM member WHERE ID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  "javaking");
			int result = pstmt.executeUpdate();
			System.out.println("삭제한 회원 수: "+result);
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
