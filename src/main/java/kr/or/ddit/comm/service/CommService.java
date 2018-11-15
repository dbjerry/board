package kr.or.ddit.comm.service;

import java.util.List;

import kr.or.ddit.comm.dao.CommDao;
import kr.or.ddit.comm.model.CommVO;

public class CommService implements ICommService{

	CommDao dao = new CommDao();
	
	/**
	 * Method : insertComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param commvo
	 * @return
	 * Method 설명 : 댓글 생성(저장)
	 */
	@Override
	public int insertComm(CommVO commvo) {
		return dao.insertComm(commvo);
	}

	/**
	 * Method : selectComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	@Override
	public List<CommVO> selectComm(String postsno) {
		return dao.selectComm(postsno);
	}

	/**
	 * Method : updateComm
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param commid
	 * @return
	 * Method 설명 : 댓글 삭제 구분
	 */
	@Override
	public int updateComm(String commid) {
		return dao.updateComm(commid);
	}

}

