package kr.or.ddit.db;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.posts.dao.IPostsDao;
import kr.or.ddit.posts.dao.PostsDao;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.util.model.PageVO;

import org.junit.Before;
import org.junit.Test;

public class PostsDaoTest {

	private IPostsDao dao;
	
	@Before
	public void setup(){
		dao = new PostsDao();
	}
	
	/**
	 * Method : postsPageListTest
	 * 작성자 : 김지태
	 * 변경이력 :
	 * Method 설명 : 게시글 조회(페이징처리 적용) 테스트
	 */
	@Test
	public void postsPageListTest() {
		/***Given***/
		PageVO pagevo = new PageVO();
		pagevo.setPage(1);
		pagevo.setPageSize(10);
		
		BoardVO boardvo = new BoardVO();
		boardvo.setBoard_id("00015");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagevo", pagevo);
		map.put("boardvo", boardvo);
		
		/***When***/
		List<PostsVO> postsList = dao.postsPageList(map);
		
		/***Then***/
		assertEquals(10, postsList.size());

	}

	/**
	 * Method : postsCntTest
	 * 작성자 : 김지태
	 * 변경이력 :
	 * Method 설명 : 해당 게시글 수 조회 테스트
	 */
	@Test
	public void postsCntTest(){
		/***Given***/
		
		/***When***/
		int postsCnt = dao.postsCnt("00015");
		
		/***Then***/
		assertEquals(11, postsCnt);

	}
	
	/**
	 * Method : getPostsDetailTest
	 * 작성자 : 김지태
	 * 변경이력 :
	 * Method 설명 : 게시글 상세보기 테스트
	 */
	@Test
	public void getPostsDetailTest(){
		/***Given***/

		/***When***/
		PostsVO getPosts = dao.getPostsDetail("00026");
		System.out.println(getPosts.getPosts_no());
		System.out.println(getPosts.getPosts_title());
		
		/***Then***/
		assertEquals("테스트 게시글", getPosts.getPosts_title());

	}
	
}

