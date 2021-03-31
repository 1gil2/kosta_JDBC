package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

//DAO(Data Access Object)
public class EmpDAO {
	
	//CRUD(Create : insert, Read : select, U : update, D : delete)
	
	//사용자가 웹을 통해서 삭제 
	public int deleteEmp(int emp_id) {
		int result = 0;
		String sql = "delete from employees where employee_id = ? ";
		Connection conn;
		PreparedStatement st = null;
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, emp_id);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	//사용자가 웹을 통해서 개인정보 수정 본래정보가 보인다. -> controller -> DAO -> DB	
	public int updateEmp(EmpVO emp) {
		int result = 0;
		String sql =
				"update employees set "+
			    "FIRST_NAME = ?, "+
			    "LAST_NAME  = ?, "+
			    "EMAIL  = ?, "+
			    "PHONE_NUMBER  = ?, "+
			    "HIRE_DATE  = ?, "+
			    "JOB_ID  = ?, "+
			    "SALARY  = ?, "+
			    "COMMISSION_PCT  = ?, "+  
			    "MANAGER_ID  = ?, "+
			    "DEPARTMENT_ID  = ? "+
			"where employee_id = ? ";
		Connection conn;
		PreparedStatement st = null;
		
		conn = DBUtil.getConnection();
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(11, emp.getEmployee_id());
			st.setString(1, emp.getFirst_name());
			st.setString(2, emp.getLast_name());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getPhone_number());
			st.setDate(5, emp.getHire_date());
			st.setString(6, emp.getJob_id());
			st.setInt(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			st.setInt(9, emp.getManager_id());
			st.setInt(10, emp.getDepartment_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	//사용자가 웹을 통해서 회원가입 -> controller -> DAO -> DB
	public int insertEmp(EmpVO emp) {
		String sql = "insert into employees values (?,?,?,?,?,?,?,?,?,?,?) ";
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false); //한번에 여러sql 문을 실행하고자 하는 경우 사용
			st = conn.prepareStatement(sql);
			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setInt(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setInt(10, emp.getManager_id());
			st.setInt(11, emp.getDepartment_id());
			result = st.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

	//1.모든직원조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employees";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return emplist;
	}

	//2.기본키(Primary Key)..null불가, 필수칼럼, 중복없음
	//직원번호로 조회
	public EmpVO selectById(int id) {
		EmpVO emp = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where employee_id = ?";

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return emp;
	}
	
	//3.부서로 조회
	public List<EmpVO> selectByDept(int dept_id) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where department_id = ?";

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, dept_id);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return emplist;
	}
	
	//4.job_id로 조회
	public List<EmpVO> selectByJob(String job_id) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where job_id = ?";

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, job_id);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return emplist;
	}
	
	//5.급여로 조회(얼마 사이)
	public List<EmpVO> selectBySal(int minsal, int maxsal) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where salary between ? and ? order by salary";

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, minsal);
			st.setInt(2, maxsal);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return emplist;
	}
	
	//6.입사일 조회(날짜 사이)
	public List<EmpVO> selectByDate(String sdate, String edate) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where hire_date between ? and ?";

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, sdate);
			st.setString(2, edate);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return emplist;
	}
	
	//7.이름에 특정문자가 들어간 사람 조회
	public List<EmpVO> selectByChar(String ch) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where last_name like ?";

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, "%"+ch+"%");
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return emplist;
	}
	
	//8.여러조건조회(부서, job, salary, hire_date)
	public List<EmpVO> selectByCondition(int dept, String job, int sal, Date hdate) {
		List<EmpVO> emplist = new ArrayList<EmpVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select * from employees where department_id = ?" +
					" and job_id = ?" +
					" and salary >= ?" +
					" and hire_date between ? and add_months(?, 24)";

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, dept);
			st.setString(2, job);
			st.setInt(3, sal);
			st.setDate(4, hdate);
			st.setDate(5, hdate);
			
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return emplist;
	}
	
	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
		emp.setCommission_pct(rs.getDouble("commission_pct"));
		emp.setDepartment_id(rs.getInt("department_id"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("employee_id"));
		emp.setFirst_name(rs.getString("first_name"));
		emp.setHire_date(rs.getDate("hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setLast_name(rs.getString("last_name"));
		emp.setManager_id(rs.getInt("manager_id"));
		emp.setPhone_number(rs.getString("phone_number"));
		emp.setSalary(rs.getInt("salary"));
		
		return emp;
	}



}
