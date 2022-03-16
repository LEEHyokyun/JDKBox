package model;

public class EmployeeVO {
	private int empno;
	private String ename;
	private int salary;
	private String job;
	private DepartmentVO departmentVO; // has a relationship, aggregation

	public EmployeeVO() {
		super();
	}

	public EmployeeVO(int empno, String ename, int salary, String job, DepartmentVO departmentVO) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.salary = salary;
		this.job = job;
		this.departmentVO = departmentVO;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}

	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}

	@Override
	public String toString() {
		return "EmployeeVO [empno=" + empno + ", ename=" + ename + ", salary=" + salary + ", job=" + job
				+ ", departmentVO=" + departmentVO + "]";
	}

}
