package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class Prob6 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("조회하고자 하는 직원의 이름 일부를 입력하세요>>");
		String name = sc.next();
		new Prob5().printEmployee(name);
	}
	
	public void printEmployee(String name) {
		String driverName = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "hr", password = "hr";
		try {
			conn = DriverManager.getConnection(url, userid, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql =
				"select employee_id, first_name, salary " +
				"from employees " +
				"where first_name like ?";
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, "%"+name+"%");
			rs = st.executeQuery();
			
			while(rs.next()) {
				int emp_id = rs.getInt(1);
				String fname = rs.getString(2);
				int sal = rs.getInt(3);
				System.out.println(emp_id + "\t" + fname + "\t" + sal);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
