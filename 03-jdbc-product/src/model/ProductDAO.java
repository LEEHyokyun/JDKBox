package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbInfo;

public class ProductDAO {
	public ProductDAO() throws ClassNotFoundException {
		Class.forName(DbInfo.DRIVER);
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbInfo.URL, DbInfo.USER, DbInfo.PASS);
	}
	
	public int getProductTotalCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int totalCount = 0;
		
		try {
			con = getConnection();
			String sql = "SELECT COUNT(*) FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				totalCount = rs.getInt(1);
			/*
			 * getInt - Query 수행 결과!
			 */
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}

	public ProductVO findProudctById(int id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO vo = null;
		
		con = getConnection();
		String sql = "SELECT * FROM product WHERE id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			/*
			 * id는 primary key이므로, 존재하거나 존재하지 않거나
			 */
			vo = new ProductVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			//vo = new ProductVO(rs.getInt("id"), rs.getString("name"), rs.getString("maker"), rs.getInt("price"));
		}
		
		return vo;
	}

	public void registerProduct(ProductVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = getConnection();
			String sql = "INSERT INTO product(id, name, maker, price) VALUES(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getMaker());
			pstmt.setInt(4, vo.getPrice());
			
			count = pstmt.executeUpdate();
			System.out.println(count+" columns have been updated.");
		}finally{
			closeAll(pstmt, con);
		}
	}

	public void deleteProductById(int id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try{
			con = getConnection();
			String sql = "DELETE FROM product WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			count = pstmt.executeUpdate();
			System.out.println(count+" columns have been updated.");
		}finally {
			closeAll(pstmt, con);
		}
		
	}

	public ArrayList<ProductVO> getAllproductList() throws SQLException {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "SELECT ID, NAME, MAKER, PRICE FROM product ORDER BY ID desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				/*
				 * 반복할때마다 product에 대한 새로운 객체가 생성되도록 구성
				 */
				list.add(new ProductVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				//System.out.println("등록 완료");
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

}
