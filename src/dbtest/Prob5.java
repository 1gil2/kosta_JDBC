package dbtest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import util.DBUtil;


public class Prob5 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("��ȸ�ϰ��� �ϴ� ������ �̸� �Ϻθ� �Է��ϼ���>>");
		String name = sc.next();
		new Prob5().printEmployee(name);
	}
	
	public void printEmployee(String name) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql =
				"select employee_id, first_name, salary " +
				"from employees " +
				"where first_name like ?";
		
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
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
	}
	
}

/*
����5. Ű����κ��� ������ �̸� �Ϻθ� �Է� �޾Ƽ� employees ���̺���
�ش� ����� ���(employee_id), �̸�(first_name), �޿�(salary) �� ����ϴ� �޼ҵ带 �ۼ��Ͻÿ�.

Driver Name : oracle.jdbc.driver.OracleDriver
URL  :  jdbc:oracle:thin:@localhost:1521:xe
User: hr
Password: hr

1.	printEmployee(String name) �޼ҵ带 �����մϴ�.
2.	Ű����κ��� �Է� �޾� �о���� �κ��� main �޼ҵ� ���� �����Ǿ� �ֽ��ϴ�.
3.	main �޼ҵ带 �����Ͽ� �־��� �޼ҵ��� �ñ״�ó�� �������� �ʰ� �״�� ����մϴ�.
4.	�Ʒ��� ���ô� �����̸��� ��D���� ����ִ� ����� ����� ����� ���Դϴ�.
*/