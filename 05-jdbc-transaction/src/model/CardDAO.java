package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbInfo;

public class CardDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs != null)
			rs.close();
		closeAll(pstmt, con);
	}
	//transaction의 반영
	/*
	 * 1) 수동커밋모드 > 직접 commit을 명시하지 않았을때는 DB에 반영하지 않는다.
	 * 2) rollback> 오류상황에서는 모든 작업을 이전 상태로 회귀한다.
	 */
	public void registerCardAndPoint(String id, String name, String pointType, int point) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			/*
			 * 1. 수동커밋모드
			 */
			con.setAutoCommit(false);
			String insertCardsql = "INSERT INTO card(id, name) VALUES(?, ?)";
			String insertPointsql = "INSERT INTO point(id, point_type, point) VALUES(?, ?, ?)";
			
			pstmt = con.prepareStatement(insertCardsql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);	
			int result1 = pstmt.executeUpdate();
			System.out.println("카드등록: "+result1);
			pstmt.close();
			
			pstmt = con.prepareStatement(insertPointsql);
			pstmt.setString(1, id);
			pstmt.setString(2, pointType);
			pstmt.setInt(3, point);
			int result2 = pstmt.executeUpdate();
			System.out.println("포인트등록: "+result2);
			pstmt.close();
			/*
			 * 커밋
			 */
			con.commit();
			System.out.println("작업이 정상적으로 처리되었습니다.");
		}catch(Exception e){
			/*
			 * 3. rollback
			 */
			con.rollback();
			e.printStackTrace();
			System.out.println("시스템 오류로 작업이 정상적으로 처리되지 않았습니다.");
		}finally {
			closeAll(pstmt, con);
		}
	}
}
