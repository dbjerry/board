package kr.or.ddit.posts.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.posts.model.PostsVO;

public interface IPostsDao {

	/**
	 * Method : postsPageList
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param pagevo
	 * @return
	 * Method 설명 : 게시글 리스트(페이징처리) 조회
	 */
	public List<PostsVO> postsPageList(Map<String, Object> map);
	
	/**
	 * Method : postsCnt
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 게시글 총 갯수
	 */
	public int postsCnt(String boardId);

	/**
	 * Method : insertParentPosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 원글(부모글) 작성
	 */
	public int insertParentPosts(PostsVO postsvo);
	
	/**
	 * Method : insertChildPosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 답글(자식글) 작성
	 */
	public int insertChildPosts(PostsVO postsvo);
	
	/**
	 * Method : getPostsDetail
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 게시글 상세보기
	 */
	public PostsVO getPostsDetail(String postsno);
	
	/**
	 * Method : updatePosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	public int updatePosts(PostsVO postsvo);
	
	/**
	 * Method : updateUse
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 게시글 삭제 구분 수정
	 */
	public int updateUse(String postsno);
	
	/**
	 * Method : maxPosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 최신번호
	 */
	public String maxPosts(String bid);
	
}

