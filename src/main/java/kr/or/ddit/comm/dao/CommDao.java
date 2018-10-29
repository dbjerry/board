package kr.or.ddit.comm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.comm.model.CommVO;
import kr.or.ddit.db.SqlFactoryBuilder;

public class CommDao implements ICommDao{

	/**
	 * Method : insertComm
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param commvo
	 * @return
	 * Method 설명 : 댓글 생성(저장)
	 */
	@Override
	public int insertComm(CommVO commvo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int insertComm = session.insert("comm.insertComm", commvo);
		
		session.commit();
		session.close();
		
		return insertComm;
	}

	/**
	 * Method : selectComm
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	@Override
	public List<CommVO> selectComm(String postsno) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		List<CommVO> commList = session.selectList("comm.selectComm", postsno);
		
		session.close();
		
		return commList;
	}

	/**
	 * Method : updateComm
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param commid
	 * @return
	 * Method 설명 : 댓글 삭제 구분
	 */
	@Override
	public int updateComm(String commid) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int updateComm = session.update("comm.updateComm", commid);
		
		session.commit();
		session.close();
		
		return updateComm;
	}

}

