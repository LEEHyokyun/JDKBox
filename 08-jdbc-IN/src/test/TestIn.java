package test;

import java.util.ArrayList;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestIn {
	public static void main(String[] args) {
		try {
			EmployeeDAO dao = new EmployeeDAO();
			/*
			 * s_employee table에 저장된 전체 사원의 월급 평균보다
			 * 직종별(job) 평균 월급이 낮은 job 사원에 대하여
			 * 해당 사원 정보를 조회하여 리스트로 반환받아 출력
			 */
			ArrayList<EmployeeVO> list = dao.findEmployeeList();
			for(EmployeeVO vo:list) {
				System.out.println(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
