package test.step1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.EmployeeDAO;

public class TestEmployeeGroupBy {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		/*
		 * s_employee table에 저장된 사원정보 중
		 * job에 대한 사원수 및 최고 salary 조회
		 */
		
		/*
		 * key point
		 * job, COUNT(*), MAX(SALARY) 자체 table 정보는 ArrayList에 저장
		 * 각각의 job, COUNT, MAX 정보들은 HashMap으로 key -value로 저장
		 */
		try {
			ArrayList<HashMap<String, Object>> list = dao.findJobGroupList();
			for(int i=0;i<list.size();i++) {
				HashMap<String, Object> map = list.get(i);
				System.out.println(map.get("job")+" "+map.get("empcount")+" "+map.get("highestsal"));
				/*
				 * key - job, empcount, highestsal
				 */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
