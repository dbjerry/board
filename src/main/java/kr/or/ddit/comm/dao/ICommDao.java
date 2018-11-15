package kr.or.ddit.comm.dao;

import java.util.List;

import kr.or.ddit.comm.model.CommVO;

public interface ICommDao {

	/**
	 * Method : insertComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param commvo
	 * @return
	 * Method 설명 : 댓글 생성(저장)
	 */
	public int insertComm(CommVO commvo);
	
	/**
	 * Method : selectComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	public List<CommVO> selectComm(String postsno);
	
	/**
	 * Method : updateComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param commid
	 * @return
	 * Method 설명 : 댓글 삭제 구분
	 */
	public int updateComm(String commid);
	
}

