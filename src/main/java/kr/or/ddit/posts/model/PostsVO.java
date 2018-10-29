package kr.or.ddit.posts.model;

public class PostsVO {
	private String posts_no;	//	게시글 번호(고유)
	private String board_id;	//	게시판 ID
	private String posts_use;	//	게시글 삭제 구분
	private String posts_title;	//	게시글 제목
	private String posts_cnt;	//	게시글 내용
	private String userid;		//	사용자 ID
	private String posts_date;	//	게시글 등록일자
	private String posts_hno;	//	부모게시글번호
	private int group_no;		//	그룹번호
	private int rnum;			//	게시글 번호(임시)
	
	/* 각 value의 getter & setter */
	public String getPosts_no() {
		return posts_no;
	}
	public void setPosts_no(String posts_no) {
		this.posts_no = posts_no;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getPosts_use() {
		return posts_use;
	}
	public void setPosts_use(String posts_use) {
		this.posts_use = posts_use;
	}
	public String getPosts_title() {
		return posts_title;
	}
	public void setPosts_title(String posts_title) {
		this.posts_title = posts_title;
	}
	public String getPosts_cnt() {
		return posts_cnt;
	}
	public void setPosts_cnt(String posts_cnt) {
		this.posts_cnt = posts_cnt;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPosts_date() {
		return posts_date;
	}
	public void setPosts_date(String posts_date) {
		this.posts_date = posts_date;
	}
	public String getPosts_hno() {
		return posts_hno;
	}
	public void setPosts_hno(String posts_hno) {
		this.posts_hno = posts_hno;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	@Override
	public String toString() {
		return "PostsVO [posts_no=" + posts_no + ", board_id=" + board_id
				+ ", posts_use=" + posts_use + ", posts_title=" + posts_title
				+ ", posts_cnt=" + posts_cnt + ", userid=" + userid
				+ ", posts_date=" + posts_date + ", posts_hno=" + posts_hno
				+ ", group_no=" + group_no + ", rnum=" + rnum + "]";
	}
	
}

