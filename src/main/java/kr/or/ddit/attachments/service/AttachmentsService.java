package kr.or.ddit.attachments.service;

import java.util.List;

import kr.or.ddit.attachments.dao.AttachmentsDao;
import kr.or.ddit.attachments.dao.IAttachmentsDao;
import kr.or.ddit.attachments.model.AttachmentsVO;

public class AttachmentsService implements IAttachmentsService {

	IAttachmentsDao dao = new AttachmentsDao();
	
	/**
	 * Method : insertAtta
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertAtta(AttachmentsVO attavo) {
		return dao.insertAtta(attavo);
	}

	/**
	 * Method : selectAtta
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 해당 게시글의 첨부파일 조회
	 */
	@Override
	public List<AttachmentsVO> selectAtta(String postsno) {
		return dao.selectAtta(postsno);
	}

	/**
	 * Method : deleteAtta
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param attano
	 * @return
	 * Method 설명 : 첨부파일 삭제
	 */
	@Override
	public int deleteAtta(String attano) {
		return dao.deleteAtta(attano);
	}

}
