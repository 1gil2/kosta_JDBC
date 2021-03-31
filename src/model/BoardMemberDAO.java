package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class BoardMemberDAO {
	//CRUD
	
	//1. 모두 조회
	public List<BoardVO> selectAll() {
		List<BoardVO> blist = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select * from board";
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				BoardVO board = makeBoard(rs);
				blist.add(board);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return blist;
	}

	private BoardVO makeBoard(ResultSet rs) throws SQLException {
		BoardVO board = new BoardVO();
		board.setBoard_contents(rs.getString("board_contents"));
		board.setBoard_viewcount(rs.getInt("Board_viewcount"));
		board.setBoard_date(rs.getDate("Board_date"));
		board.setBoard_image(rs.getString("Board_image"));
		board.setBoard_password(rs.getString("Board_password"));
		board.setBoard_seq(rs.getLong("Board_seq"));
		board.setBoard_title(rs.getString("Board_title"));
		board.setBoard_writer(rs.getInt("Board_writer"));
		return board;
	}

	//2. 상세 보기 (Board번호로 상세보기)
	public BoardVO selectByNo(int boardNo) {
		BoardVO board = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select * from board where board_seq = ?";
		String sql2 = " update board set board_viewcount = board_viewcount+1 where Board_seq = ?";
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, boardNo);
			rs = st.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
				st = conn.prepareStatement(sql2);
				st.setInt(1, boardNo);
				int result = st.executeUpdate();
				System.out.println(result>0?"view_count수정성공":"view_count수정실패");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return board;
	}
	
	
	//3. 입력
	public int boardInsert(BoardVO board) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		String sql = " insert into board values (board_autonum.nextval, ?, ?, ?, sysdate, 0, ?, ?)";

		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, board.getBoard_title());
			st.setString(2, board.getBoard_contents());
			st.setInt(3, board.getBoard_writer());
			st.setString(4, board.getBoard_password());
			st.setString(5, board.getBoard_image());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

	//4. 수정
	public int boardUpdate(BoardVO board) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		
		String sql =
				" update board" +
				" set board_title = ?, board_contents = ?, board_date = sysdate, board_password = ?, board_image =?" +
				" where board_seq = ?";
		conn =DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, board.getBoard_title());
			st.setString(2, board.getBoard_contents());
			st.setString(3, board.getBoard_password());
			st.setString(4, board.getBoard_image());
			st.setLong(5, board.getBoard_seq());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		
		return result;
	}
	
	//5. 삭제
	public int boardDelete(int boardNo, String pw) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		
		String sql =
				" delete from board " +
				" where board_seq = ? and  board_password = ?";
		
		conn =DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setLong(1, boardNo);
			st.setString(2, pw);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

}
