package model;

import java.sql.Date;

public class BoardVO {
	private long board_seq;
	private String board_title;
	private String board_contents;
	private int board_writer;
	private Date board_date;
	private int board_viewcount;
	private String board_password;
	private String board_image;
	
	public BoardVO() {
		super();
	}

	public BoardVO(long board_seq, String board_title, String board_contents, int board_writer, Date board_date,
			int board_viewcount, String board_password, String board_image) {
		super();
		this.board_seq = board_seq;
		this.board_title = board_title;
		this.board_contents = board_contents;
		this.board_writer = board_writer;
		this.board_date = board_date;
		this.board_viewcount = board_viewcount;
		this.board_password = board_password;
		this.board_image = board_image;
	}

	public long getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(long board_seq) {
		this.board_seq = board_seq;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_contents() {
		return board_contents;
	}

	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}

	public int getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(int board_writer) {
		this.board_writer = board_writer;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public int getBoard_viewcount() {
		return board_viewcount;
	}

	public void setBoard_viewcount(int board_viewcount) {
		this.board_viewcount = board_viewcount;
	}

	public String getBoard_password() {
		return board_password;
	}

	public void setBoard_password(String board_password) {
		this.board_password = board_password;
	}

	public String getBoard_image() {
		return board_image;
	}

	public void setBoard_image(String board_image) {
		this.board_image = board_image;
	}

	@Override
	public String toString() {
		return "BoardVO [board_seq=" + board_seq + ", board_title=" + board_title + ", board_contents=" + board_contents
				+ ", board_writer=" + board_writer + ", board_date=" + board_date + ", board_viewcount="
				+ board_viewcount + ", board_password=" + board_password + ", board_image=" + board_image + "]";
	}


}
