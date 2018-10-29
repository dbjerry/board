package kr.or.ddit.attachments.service;

import java.util.List;

import kr.or.ddit.attachments.model.AttachmentsVO;

public interface IAttachmentsService {

	/**
	 * Method : insertAtta
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	public int insertAtta(AttachmentsVO attavo);
	
	/**
	 * Method : selectAtta
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 해당 게시글의 첨부파일 조회
	 */
	public List<AttachmentsVO> selectAtta(String postsno);
	
	/**
	 * Method : deleteAtta
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param attano
	 * @return
	 * Method 설명 : 첨부파일 삭제
	 */
	public int deleteAtta(String attano);
	
}

