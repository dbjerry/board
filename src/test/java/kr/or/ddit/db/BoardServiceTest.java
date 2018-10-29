package kr.or.ddit.db;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

import org.junit.Before;
import org.junit.Test;

public class BoardServiceTest {
	
	private IBoardService service;
	
	@Before
	public void setup(){
		service = new BoardService();
	}
	
	/**
	 * Method : selectAllBoardTest
	 * 작성자 : 김지태
	 * 변경이력 :
	 * Method 설명 : 게시판 전체 조회 테스트
	 */
	@Test
	public void selectAllBoardTest() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = service.selectAllBoard();
		
		/***Then***/
		assertEquals(5, boardList.size());
		
	}

}

