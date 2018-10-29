package kr.or.ddit.attachments.model;

public class AttachmentsVO {
	
	private String atta_no;		//	첨부파일 번호
	private String atta_file;	//	첨부파일 경로
	private String posts_no;	//	게시판 번호(고유)

	/* 각 value의 getter & setter */
	public String getAtta_no() {
		return atta_no;
	}
	public void setAtta_no(String atta_no) {
		this.atta_no = atta_no;
	}
	public String getAtta_file() {
		return atta_file;
	}
	public void setAtta_file(String atta_file) {
		this.atta_file = atta_file;
	}
	public String getPosts_no() {
		return posts_no;
	}
	public void setPosts_no(String posts_no) {
		this.posts_no = posts_no;
	}
	
}

