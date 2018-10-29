package kr.or.ddit.posts.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.db.SqlFactoryBuilder;
import kr.or.ddit.posts.model.PostsVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PostsDao implements IPostsDao{

	/**
	 * Method : postsPageList
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param pagevo
	 * @return
	 * Method 설명 : 게시글 리스트(페이징처리) 조회
	 */
	@Override
	public List<PostsVO> postsPageList(Map<String, Object> map) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		List<PostsVO> pageList = session.selectList("posts.postsPageList", map);
		
		session.close();
		
		return pageList;
	}

	/**
	 * Method : postsCnt
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 게시글 총 갯수
	 */
	@Override
	public int postsCnt(String boardId) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int postsCnt = session.selectOne("posts.postsCnt", boardId);
		
		session.close();
		
		return postsCnt;
	}

	/**
	 * Method : insertParentPosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 원글(부모글) 작성
	 */
	@Override
	public int insertParentPosts(PostsVO postsvo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int insertPosts = session.insert("posts.insertParentPosts", postsvo);
		
		session.commit();
		session.close();
		
		return insertPosts;
	}

	/**
	 * Method : insertChildPosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 답글(자식글) 작성
	 */
	@Override
	public int insertChildPosts(PostsVO postsvo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int insertPosts = session.insert("posts.insertChildPosts", postsvo);
		
		session.commit();
		session.close();
		
		return insertPosts;
	}

	/**
	 * Method : getPostsDetail
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 게시글 상세보기
	 */
	@Override
	public PostsVO getPostsDetail(String postsno) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		PostsVO getPosts = session.selectOne("posts.getPostsDetail", postsno);
		
		session.close();
		
		return getPosts;
	}

	/**
	 * Method : updatePosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updatePosts(PostsVO postsvo) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int updatePosts = session.update("posts.updatePosts", postsvo);
		
		session.commit();
		session.close();
		
		return updatePosts;
	}

	/**
	 * Method : updateUse
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 게시글 삭제 구분 수정
	 */
	@Override
	public int updateUse(String postsno) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int updateUse = session.update("posts.updateUse", postsno);
		
		session.commit();
		session.close();
		
		return updateUse;
	}

	/**
	 * Method : maxPosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 최신번호
	 */
	@Override
	public String maxPosts(String bid) {
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		String maxPosts = (String) session.selectOne("posts.maxPosts", bid);
		
		session.close();
		
		return maxPosts;
	}

}

