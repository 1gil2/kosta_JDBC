package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.EmpDAO;
import model.EmpVO;
import view.EmpView;

public class EmpController {

	public static void main(String[] args) {
		
		//입력test
//		mothod9();
		//수정test
//		method10();
		//삭제test
		method11();
		
		
		//1.모든직원조회
		//2.기본키(Primary Key)..null불가, 필수칼럼, 중복없음
		//직원번호로 조회
		//3.부서로 조회
		//4.job_id로 조회
		//5.급여로 조회
		//6.입사일 조회 
//		method6("2005/01/01", "2006/12/31");	
		//7.이름에 특정문자가 들어간 사람 조회
		//8.여러조건조회(부서, job, salary, hire_date)
//		method8(80, "SA_REP", 10000, "2005-01-01");
		
	}
	
	
	private static void method11() {
		EmpDAO dao = new EmpDAO();
		int result =dao.deleteEmp(100);
		EmpView.display(result>0?"삭제성공":"삭제실패");
	}


	private static void method10() {
		EmpDAO dao = new EmpDAO();
		EmpVO emp = makeEmp();
		int result =dao.updateEmp(emp);
		EmpView.display(result>0?"수정성공":"수정실패");
		
	}


	private static void mothod9() {
		EmpDAO dao = new EmpDAO();
		EmpVO emp = makeEmp();
		int result = dao.insertEmp(emp);
		EmpView.display(result>0?"입력성공":"입력실패");
		
	}

	private static EmpVO makeEmp(){
		EmpVO emp = new EmpVO();
		emp.setCommission_pct(0.7);
		emp.setDepartment_id(30);
		emp.setEmail("aaqazxcv8640@gmail.com");
		emp.setEmployee_id(999);
		emp.setFirst_name("C1hoi");
		java.util.Date utilDate = new java.util.Date();
		emp.setHire_date(new Date(utilDate.getTime()));
		emp.setJob_id("IT_PROG");
		emp.setLast_name("hxangilllll");
		emp.setManager_id(100);
		emp.setPhone_number("010-8765-2222");
		emp.setSalary(1000);
		
		return emp;
	}

	private static void method8(int dept, String job, int sal, String hdate) {
		EmpDAO dao = new EmpDAO();
		Date dt = Date.valueOf(hdate);
		List<EmpVO> emplist = dao.selectByCondition(dept, job, sal, dt);
		EmpView.display(emplist);
		
	}


	private static void method7(String ch) {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> emplist = dao.selectByChar(ch);
		EmpView.display(emplist);
		
	}

	private static void method6(String sdt, String edt) {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> emplist = dao.selectByDate(sdt, edt);
		EmpView.display(emplist);
		
	}
	
	private static void method5(int minsal, int maxsal) {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> emplist = dao.selectBySal(minsal, maxsal);
		EmpView.display(emplist);
		
	}
	
	//??DAO는 static으로 안하는지 -> view는 별거 없어서 메모리 먹여도 ㄱㅊ, 근데 dao는 존나 큼
	private static void method4(String job_id) {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> emplist = dao.selectByJob(job_id);
		EmpView.display(emplist);
		
	}

	private static void method3(int dept_id) {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> emplist = dao.selectByDept(dept_id);
		EmpView.display(emplist);
		
	}

	private static void method2(int emp_id) {
		EmpDAO dao = new EmpDAO();
		EmpVO emp = dao.selectById(emp_id);
		EmpView.display(emp);
		
	}

	private static void method1() {
		EmpDAO dao = new EmpDAO();
		List<EmpVO> emplist = dao.selectAll();
		EmpView.display(emplist);

	}

}
