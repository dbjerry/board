package kr.or.ddit.db;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.comm.model.CommVO;
import kr.or.ddit.comm.service.CommService;
import kr.or.ddit.comm.service.ICommService;

import org.junit.Before;
import org.junit.Test;

public class CommDaoTest {
	
	ICommService service;
	
	@Before
	public void setup(){
		service = new CommService();
	}
	
	/**
	 * Method : selectCommTest
	 * 작성자 : 김지태
	 * 변경이력 :
	 * Method 설명 : 댓글 조회 테스트
	 */
	@Test
	public void selectCommTest() {
		/***Given***/

		/***When***/
		List<CommVO> commList = service.selectComm("00040");
		System.out.println("commList.size() : " + commList.size());
		
		/***Then***/
		assertEquals(1, commList.size());

	}

}

