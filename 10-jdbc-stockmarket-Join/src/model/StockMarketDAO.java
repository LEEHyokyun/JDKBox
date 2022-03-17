package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DbInfo;

public class StockMarketDAO {
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

	public ArrayList<SharesVO> findSharesListById(String id) throws SQLException {
		ArrayList<SharesVO> list = new ArrayList<SharesVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder("SELECT c.id, c.password, c.name, c.address, s.symbol, s.price, sh.quantity ");
			sql.append("FROM shares sh ");
			sql.append("INNER JOIN customer c ON c.id = sh.id ");
			sql.append("INNER JOIN stock s ON s.symbol = sh.symbol ");
			sql.append("WHERE c.id = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SharesVO vo = new SharesVO();
				vo.setName(rs.getString("name"));
				vo.setSymbol(rs.getString("symbol"));
				vo.setQuantity(rs.getInt("quantity"));
				//vo.setTotalPrice(rs.getInt("total_price"));
				vo.setTotalPrice(rs.getInt("quantity")*rs.getInt("price"));
				list.add(vo);
			}
			
			return list;
			
		}finally{
			closeAll(rs, pstmt, con);
		}
	}
}
