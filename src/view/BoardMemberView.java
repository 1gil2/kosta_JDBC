package view;

import java.util.List;

import model.BoardVO;

public class BoardMemberView {
	
	public static void display(List<BoardVO> blist) {
		System.out.println("-----BoardList 출력-----");
		for(BoardVO board : blist) {
			System.out.println(board);
		}
		
	}
	
	public static void display(BoardVO board) {
		System.out.println("-----Board 1건 출력-----");
		System.out.println(board);
		
	}
	
	public static void display(String message) {
		System.out.println("-----알림-----");
		System.out.println(message);
		
	}

}
