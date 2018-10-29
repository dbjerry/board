package kr.or.ddit.db;

import static org.junit.Assert.assertEquals;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {

	private IUserService service;
	
	@Before
	public void setup(){
		service = new UserService();
	}
	
	/**
	 * Method : selectUserTest
	 * 작성자 : 김지태
	 * 변경이력 :
	 * Method 설명 : 사용자 검색 테스트
	 */
	@Test
	public void selectUserTest() {
		/***Given***/

		/***When***/
		UserVO user = service.selectUser("minions");

		/***Then***/
		assertEquals("minions", user.getName());

	}

}

