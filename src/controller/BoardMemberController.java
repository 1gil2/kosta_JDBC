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
			System.out.println("-----------�۾�����---------------");
			System.out.println("0. �α��� 1. �����ȸ 2. ��ȣ����ȸ 3. �Է� 4. ���� 5. ���� 9. exit");
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
				System.out.print("��ȣ�� �Է��Ͻÿ� > ");
				no = sc.nextInt();
				BoardMemberView.display(boardDAO.selectByNo(no));
				break;
			case 3:
				if(user == 0 || pw == null) {
					System.out.println("�α��� �ؾ���, 0�� �ϰ� ���ÿ�.");
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
				BoardMemberView.display(result>0?"�Է¼���":"�Է½���");
				break;
			case 4:
				if(user == 0 || pw == null) {
					System.out.println("�α��� �ؾ���, 0�� �ϰ� ���ÿ�.");
					break;
				}
				board = new BoardVO();
				System.out.println("����� �����ҷ� >> ");
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
				BoardMemberView.display(result>0?"��������":"��������");
				break;

			case 5:
				if(user == 0 || pw == null) {
					System.out.println("�α��� �ؾ���, 0�� �ϰ� ���ÿ�.");
					break;
				}
				System.out.print("������ no, pw >> ");
				BoardMemberView.display(boardDAO.boardDelete(sc.nextInt(), sc.next()) + "�� ����");
				
				break;
			case 9:
				System.out.println("��");
				System.exit(0);
			
			}
		}

		//board insert
//		int user = 10;
//		String pass = "�н�����";
//
//		//		BoardVO board = new BoardVO(0, "test_title", "test_contests", user, null, 0, pass, "�̹������");
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
//			BoardMemberView.display(result>0?"�Է¼���":"�Է½���");
//		}
		
		//board select
//		BoardMemberView.display(boardDAO.selectAll());
//		BoardMemberView.display(boardDAO.selectByNo(36));
		
		//board update
//		BoardVO board = new BoardVO(35, "����_title", "����_contests", 10, null, 0, "����pw", "�����̹������");
//		BoardMemberView.display(boardDAO.boardUpdate(board)+"�� ����");
//		System.out.println();
//		BoardMemberView.display(boardDAO.selectByNo(35));
		
		//board delete
//		BoardMemberView.display(boardDAO.boardDelete(36, "�н�����") + "�� ����");
		
	}


}
