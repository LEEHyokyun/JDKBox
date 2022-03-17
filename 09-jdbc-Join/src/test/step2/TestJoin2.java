package test.step2;

import java.util.ArrayList;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestJoin2 {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		try {
			String job = "총무";
			ArrayList<EmployeeVO> list = dao.findEmployeeListByJob(job);
			for(EmployeeVO empvo:list) {
				System.out.println(empvo.getEname()+" "+empvo.getSalary()+" "+empvo.getDepartmentVO().getDname());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
