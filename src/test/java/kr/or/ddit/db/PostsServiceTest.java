package kr.or.ddit.db;

import static org.junit.Assert.assertEquals;
import kr.or.ddit.posts.service.IPostsService;
import kr.or.ddit.posts.service.PostsService;

import org.junit.Before;
import org.junit.Test;

public class PostsServiceTest {
	IPostsService service;
	
	@Before
	public void setup(){
		service = new PostsService();
	}
	
	/**
	 * Method : maxPostsTest
	 * 작성자 : 김지태
	 * 변경이력 :
	 * Method 설명 : 최신게시글 번호 조회
	 */
	@Test
	public void maxPostsTest() {
		/***Given***/

		/***When***/
		String maxPosts = service.maxPosts("00015");

		/***Then***/
		assertEquals(00050, maxPosts);

	}

}
