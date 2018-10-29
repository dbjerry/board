package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardDao {
	
	/**
	 * Method : insertBoard
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param boardvo
	 * @return
	 * Method 설명 : 게시판 추가, Mybatis Query insert 성공시 1 (여러개여도 1) 반환
	 */
	public int insertBoard(BoardVO boardvo);
	
	/**
	 * Method : selectAllBoard
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 전체 조회
	 */
	public List<BoardVO> selectAllBoard();
	
	/**
	 * Method : updateBoard
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param boardvo
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	public int updateBoard(BoardVO boardvo);
	
}

