package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.DbInfo;

public class EmployeeDAO {
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

	public ArrayList<HashMap<String, Object>> findJobGroupList() throws SQLException {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			/*
			 * 띄어쓰기 주의
			 */
			StringBuilder sql = new StringBuilder("SELECT job, COUNT(*), MAX(SALARY) ");
			sql.append("FROM s_employee ");
			sql.append("GROUP BY job ");
			sql.append("ORDER BY MAX(SALARY) DESC");

			// String sql = "SELECT job, COUNT(*), MAX(SALARY) FROM s_employee GROUP BY job
			// ORDER BY MAX(SALARY) DESC";
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("job", rs.getString(1));
				map.put("empcount", rs.getInt(2));
				map.put("highestsal", rs.getInt(3));
				list.add(map);
			}
			return list;
		} finally {
			closeAll(rs, pstmt, con);
		}
	}

}
