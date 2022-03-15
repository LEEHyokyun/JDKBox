package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.DbInfo;

public class ProductDAO {
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
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}
	
	/*
	 * product table의 평균가보다 낮은 maker 조회
	 */
	public ArrayList<HashMap<String, Object>> findProductMakerGroupLessThanAvgPrice() throws SQLException {
		ArrayList<HashMap<String, Object>> list = new  ArrayList<HashMap<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder("SELECT MAKER, AVG(PRICE) ");
			sql.append("FROM product ");
			sql.append("GROUP BY MAKER ");
			sql.append("HAVING AVG(PRICE) < (SELECT AVG(PRICE) FROM product) ");
			sql.append("ORDER BY AVG(PRICE) DESC");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("maker", rs.getString(1));
				map.put("avgprice", rs.getString(2));
				list.add(map);
			}
			
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	
}
