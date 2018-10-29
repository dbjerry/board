package kr.or.ddit.board.model;

public class BoardVO {
	
	private String board_id;	//	게시판 ID
	private String board_name;	//	게시판 이름
	private String board_yn;	//	게시판 사용여부
	private String board_date;	//	게시판 등록일
	private String userId;		//	작성자(회원아이디)
	
	/* 각 value getter & setter */
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_yn() {
		return board_yn;
	}
	public void setBoard_yn(String board_yn) {
		this.board_yn = board_yn;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}

