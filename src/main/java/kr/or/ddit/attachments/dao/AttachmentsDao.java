package kr.or.ddit.attachments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.db.SqlFactoryBuilder;

public class AttachmentsDao implements IAttachmentsDao {

	/**
	 * Method : insertAtta
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertAtta(AttachmentsVO attavo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int insertAtta = session.insert("atta.insertAtta", attavo);
		
		session.commit();
		session.close();
		
		return insertAtta;
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
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		List<AttachmentsVO> attaList = session.selectList("atta.selectAtta", postsno);
		
		session.close();
		
		return attaList;
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
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int deleteAtta = session.delete("atta.deleteAtta", attano);
		
		session.commit();
		session.close();
		
		return deleteAtta;
	}

}

