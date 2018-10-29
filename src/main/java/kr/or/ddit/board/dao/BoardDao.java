package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.db.SqlFactoryBuilder;

public class BoardDao implements IBoardDao{

	/**
	 * Method : insertBoard
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param boardvo
	 * @return
	 * Method 설명 : 게시판 추가, Mybatis Query insert 성공시 1 (여러개여도 1) 반환
	 */
	@Override
	public int insertBoard(BoardVO boardvo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int insertCnt = session.insert("board.insertBoard", boardvo);
		
		session.commit();
		session.close();
		
		return insertCnt;
	}

	/**
	 * Method : selectAllBoard
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 전체 조회
	 */
	@Override
	public List<BoardVO> selectAllBoard() {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		List<BoardVO> boardList = session.selectList("board.selectAllBoard");
		
		session.close();
		
		return boardList;
	}

	/**
	 * Method : updateBoard
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param boardvo
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	@Override
	public int updateBoard(BoardVO boardvo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int updateCnt = session.update("board.updateBoard", boardvo);
		
		session.commit();
		session.close();
		
		return updateCnt;
	}

}

