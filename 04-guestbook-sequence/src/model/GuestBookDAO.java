package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbInfo;

public class GuestBookDAO {
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

	public void registerBook(GuestBookVO guestBookVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		//try - finally는 기본!
		try {
			con = getConnection();
			//일단은 쉽게 생각하자..
			String sql = "INSERT INTO guestbook(guest_no, title, content) VALUES(guestbook_seq.nextval, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, guestBookVO.getTitle());
			pstmt.setString(2, guestBookVO.getContent());
			count = pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
		
		
		System.out.println(count+" columns have been updated");
	}

	public ArrayList<GuestBookVO> getAllGuestBookList() throws SQLException {
		ArrayList<GuestBookVO> list = new ArrayList<GuestBookVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql = "SELECT GUEST_NO, TITLE, CONTENT FROM guestbook ORDER BY GUEST_NO DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new GuestBookVO(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
				
		return list;
	}

	public ArrayList<GuestBookVO> findGuestBookListByNo(int startNo, int endNo) throws SQLException {
		ArrayList<GuestBookVO> list = new ArrayList<GuestBookVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			//String sql = "SELECT GUEST_NO, TITLE, CONTENT FROM guestbook WHERE GUEST_NO BETWEEN ? AND ? ORDER BY GUEST_NO DESC";
			StringBuilder sql = new StringBuilder("SELECT GUEST_NO, TITLE, CONTENT FROM guestbook ");
			sql.append("WHERE GUEST_NO BETWEEN ? AND ? ");
			sql.append("ORDER BY GUEST_NO DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			rs = pstmt.executeQuery();
			while(rs.next())
				list.add(new GuestBookVO(rs.getInt(1), rs.getString(2), rs.getString(3)));
			
		}finally {
			closeAll(rs, pstmt, con);
		}
		
		return list;
		
	}

	public ArrayList<GuestBookVO> findGuestBookListLikeKeyword(String keyword) throws SQLException {
		ArrayList<GuestBookVO> list = new ArrayList<GuestBookVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql = "SELECT GUEST_NO, TITLE, CONTENT FROM guestbook WHERE title LIKE '%'||?||'%'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new GuestBookVO(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
				
		return list;
	}
}
