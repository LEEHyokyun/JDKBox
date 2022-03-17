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

	public ArrayList<EmployeeVO> findEmployeeListByJob(String job) throws SQLException {
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();

			StringBuilder sql = new StringBuilder("SELECT e.empno, e.ename, e.sal, e.job, e.deptno, d.dname, d.loc, d.tel ");
			sql.append("FROM department d ");
			sql.append("INNER JOIN k_employee e ON e.deptno = d.deptno ");
			sql.append("WHERE job = ? ");
			sql.append("ORDER BY sal DESC ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, job);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DepartmentVO dvo = new DepartmentVO(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
				/*
				 * 생성자 초기화가 아닌 set초기화를 통한 개별적 구성
				 */
				//DepartmentVO dvo = new DepartmentVO();
				//dvo.setDname(rs.getString("dname"));
				//dvo.setLoc(rs.getString("loc"));
				EmployeeVO vo = new EmployeeVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), dvo);
				/*
				 * set초기화를 통한 개별적 구성
				 */
				
				list.add(vo);
			}
			return list;
			
		}finally {
			closeAll(rs, pstmt ,con);
		}
	}

}
