package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.model.BoardVO;

public class BoardService implements IBoardService{

	BoardDao dao = new BoardDao();
	
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
		return dao.insertBoard(boardvo);
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
		return dao.selectAllBoard();
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
		return dao.updateBoard(boardvo);
	}

}

