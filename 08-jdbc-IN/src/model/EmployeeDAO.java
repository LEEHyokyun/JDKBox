package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public ArrayList<EmployeeVO> findEmployeeList() throws SQLException {
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder("SELECT EMPNO, NAME, JOB, SALARY ");
			sql.append("FROM s_employee ");
			sql.append("WHERE JOB IN ( ");
			sql.append("SELECT JOB ");
			sql.append("FROM s_employee ");
			sql.append("GROUP BY JOB ");
			sql.append("HAVING AVG(SALARY) < (SELECT AVG(SALARY) FROM s_employee) ");
			sql.append(") ");
			sql.append("ORDER BY SALARY DESC");
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new EmployeeVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return list;
		}finally {
			closeAll(rs, pstmt, con);
		}
	}
}
