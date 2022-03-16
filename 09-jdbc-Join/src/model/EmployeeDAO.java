package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public EmployeeVO findEmployeeByEmpNo(int empno) throws SQLException {
		DepartmentVO dvo = null;
		EmployeeVO evo = null;
		/*
		 * NullPointerException 유의할것.
		 */
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			String sql = "SELECT e.empno, e.ename, e.sal, e.job, d.deptno, d.dname, d.loc, d.tel "
					+ "FROM department d "
					+ "INNER JOIN k_employee e ON d.deptno = e.deptno "
					+ "WHERE e.empno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //empno는 유일자, 식별자이므로 1명이 존재하거나, 존재하지않거나.
				/*
				 * column 명으로 가능 -> rs.get("deptno")
				 */
				dvo = new DepartmentVO(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
				evo = new EmployeeVO(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4), dvo);
			}
			return evo;
		}finally {
			closeAll(rs, pstmt, con);
		}

	}

}
