package view;

import java.util.List;

import model.EmpVO;

public class EmpView {

	public static void display(List<EmpVO> emplist) {
		System.out.println("-----직원의 정보 여러건 출력-----");
		for(EmpVO emp : emplist) {
			System.out.println("****" + emp.getFirst_name() + "****");
			System.out.println(emp);
		}
	}

	public static void display(EmpVO emp) {
		System.out.println("-----직원의 정보 1건 출력-----");
		System.out.println("****" + emp.getFirst_name() + "****");
		System.out.println(emp);
	}

	public static void display(String message) {
		System.out.println("-----알림-----");
		System.out.println(message);
		System.out.println();
	}
}
