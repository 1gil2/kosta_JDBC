package controller;


import java.util.Scanner;

import model.BoardMemberDAO;
import model.BoardVO;
import view.BoardMemberView;

public class BoardMemberController {

	public static void main(String[] args) {

//		BoardMemberDAO boardDAO = new BoardMemberDAO();
		
		Scanner sc = new Scanner(System.in);
		BoardVO board = null;
		BoardMemberDAO boardDAO = new BoardMemberDAO();
		int no = 0;
		int user = 0;
		String pw = null;
		int result = 0;
		
		while(true) {
			System.out.println("-----------작업선택---------------");
			System.out.println("0. 로그인 1. 모두조회 2. 번호로조회 3. 입력 4. 수정 5. 삭제 9. exit");
			int work = sc.nextInt();
			switch (work) {
			case 0:
				System.out.print("ID >> ");
				user = sc.nextInt();
				System.out.print("PW >> ");
				pw = sc.next();
				break;
			case 1:
				BoardMemberView.display(boardDAO.selectAll());
				break;
			case 2:
				System.out.print("번호를 입력하시오 > ");
				no = sc.nextInt();
				BoardMemberView.display(boardDAO.selectByNo(no));
				break;
			case 3:
				if(user == 0 || pw == null) {
					System.out.println("로그인 해야함, 0번 하고 오시오.");
					break;
				}
				board = new BoardVO();
				System.out.print("title >> ");
				board.setBoard_title(sc.next());
				System.out.print("contents >> ");
				board.setBoard_contents(sc.next());
				board.setBoard_writer(user);
				board.setBoard_password(pw);
				System.out.print("image >> ");
				board.setBoard_image(sc.next());
				
				result = boardDAO.boardInsert(board);
				BoardMemberView.display(result>0?"입력성공":"입력실패");
				break;
			case 4:
				if(user == 0 || pw == null) {
					System.out.println("로그인 해야함, 0번 하고 오시오.");
					break;
				}
				board = new BoardVO();
				System.out.println("몇번을 수정할래 >> ");
				board.setBoard_seq(sc.nextLong());
				System.out.print("title >> ");
				board.setBoard_title(sc.next());
				System.out.print("contents >> ");
				board.setBoard_contents(sc.next());
				System.out.print("pw >> ");
				pw = sc.next();
				board.setBoard_password(pw);
				System.out.print("image >> ");
				board.setBoard_image(sc.next());
				
				result = boardDAO.boardUpdate(board);
				BoardMemberView.display(result>0?"수정성공":"수정실패");
				break;

			case 5:
				if(user == 0 || pw == null) {
					System.out.println("로그인 해야함, 0번 하고 오시오.");
					break;
				}
				System.out.print("삭제할 no, pw >> ");
				BoardMemberView.display(boardDAO.boardDelete(sc.nextInt(), sc.next()) + "건 삭제");
				
				break;
			case 9:
				System.out.println("끝");
				System.exit(0);
			
			}
		}

		//board insert
//		int user = 10;
//		String pass = "패스워드";
//
//		//		BoardVO board = new BoardVO(0, "test_title", "test_contests", user, null, 0, pass, "이미지경로");
//		for(int i=31; i<40;i++) {
//			BoardVO board = new BoardVO();
//			System.out.println(board);
//			board.setBoard_title("test_title"+i);
//			board.setBoard_contents("test_contents"+i);
//			board.setBoard_writer(user);
//			board.setBoard_password(pass);
//			board.setBoard_image("test/logo" + i + ".png");
//
//			int result = boardDAO.boardInsert(board);
//			BoardMemberView.display(result>0?"입력성공":"입력실패");
//		}
		
		//board select
//		BoardMemberView.display(boardDAO.selectAll());
//		BoardMemberView.display(boardDAO.selectByNo(36));
		
		//board update
//		BoardVO board = new BoardVO(35, "수정_title", "수정_contests", 10, null, 0, "수정pw", "수정이미지경로");
//		BoardMemberView.display(boardDAO.boardUpdate(board)+"건 수정");
//		System.out.println();
//		BoardMemberView.display(boardDAO.selectByNo(35));
		
		//board delete
//		BoardMemberView.display(boardDAO.boardDelete(36, "패스워드") + "건 삭제");
		
	}


}
