package test.step1;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestJoin1 {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		int empno = 3;
		try {
			EmployeeVO vo = dao.findEmployeeByEmpNo(empno);
			if(vo != null) {
				System.out.println("사원번호는 "+vo.getEmpno()+"입니다.");
				System.out.println("사원명은 "+vo.getEname()+"입니다.");
				System.out.println("월급은 "+vo.getSalary()+"입니다.");
				System.out.println("부서번호는 "+vo.getDepartmentVO().getDeptno());
				System.out.println("부서명은 "+vo.getDepartmentVO().getDname());
				System.out.println("근무지는 "+vo.getDepartmentVO().getLoc());
			}else {
				System.out.println(empno+"에 해당하는 사원번호가 없습니다");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
