package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbInfo;

/*
 * DAO, Database access Object, 
 * database에 접근하기 위한 객체
 */
public class MemberDAO {
	public MemberDAO() throws ClassNotFoundException {
		Class.forName(DbInfo.DRIVER); //v1.6 이상에서는 이 loading 과정은 생략 가능
	}
	
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if(pstmt != null)
			pstmt.close();
		if(con != null)
			con.close();
	}
	//method overloading
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs != null)
			rs.close();
		closeAll(pstmt, con);
	}
	public MemberVO findMemberById(String id) throws SQLException {
		MemberVO vo = null;
		
		//최종적으로 exception throw를 받는 대상은 main
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
			String sql = "SELECT PASSWORD, NAME, ADDRESS FROM member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//구해준 index 값을 생성자로써 전달
				return new MemberVO(id, rs.getString(1),rs.getString(2),rs.getString(3));
				//return new MemberVO(id, rs.getString("PASSWORD"), ...);
				//index, COLUMN명 모두 가능!!
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return null;
	}

	public void registerMember(MemberVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			con = DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
			String sql = "INSERT INTO member(ID, PASSWORD, NAME, ADDRESS) VALUES(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddress());
			
			int count = pstmt.executeUpdate();
			System.out.println(count+"colums updated");
		}finally {
			closeAll(pstmt, con);
		}
	}
}
