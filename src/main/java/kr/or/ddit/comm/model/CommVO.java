package kr.or.ddit.comm.model;

public class CommVO {
	
	private String comm_id;		//	댓글 ID
	private String posts_no;	//	게시글 번호
	private String comm_use;	//	댓글 삭제 구분
	private String comm_cnt;	//	댓글 내용
	private String comm_date;	//	댓글 등록일시
	private String userid;		//	사용자 ID
	
	/* 각 value의 getter & setter */
	public String getComm_id() {
		return comm_id;
	}
	public void setComm_id(String comm_id) {
		this.comm_id = comm_id;
	}
	public String getPosts_no() {
		return posts_no;
	}
	public void setPosts_no(String posts_no) {
		this.posts_no = posts_no;
	}
	public String getComm_use() {
		return comm_use;
	}
	public void setComm_use(String comm_use) {
		this.comm_use = comm_use;
	}
	public String getComm_cnt() {
		return comm_cnt;
	}
	public void setComm_cnt(String comm_cnt) {
		this.comm_cnt = comm_cnt;
	}
	public String getComm_date() {
		return comm_date;
	}
	public void setComm_date(String comm_date) {
		this.comm_date = comm_date;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}

