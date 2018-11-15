package kr.or.ddit.posts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.attachments.dao.AttachmentsDao;
import kr.or.ddit.attachments.dao.IAttachmentsDao;
import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.posts.dao.IPostsDao;
import kr.or.ddit.posts.dao.PostsDao;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.util.model.PageVO;

public class PostsService implements IPostsService{

	IPostsDao dao = new PostsDao();
	
	/**
	 * Method : postsPageList
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param pagevo
	 * @return
	 * Method 설명 : 게시글 리스트(페이징처리) 조회
	 */
	@Override
	public Map<String, Object> postsPageList(Map<String, Object> map) {
		
		List<PostsVO> postsList = dao.postsPageList(map);
		
		BoardVO boardvo = (BoardVO) map.get("boardvo");
		
		int postsCnt = dao.postsCnt(boardvo.getBoard_id());
		
		PageVO page = (PageVO) map.get("pagevo");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("postsList", postsList);
		resultMap.put("postsCnt", 
				(int)Math.ceil((double)postsCnt / page.getPageSize()));
		
		return resultMap;
	}

	/**
	 * Method : postsCnt
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param boardId
	 * @return
	 * Method 설명 : 게시글 총 갯수
	 */
	@Override
	public int postsCnt(String boardId) {
		return dao.postsCnt(boardId);
	}

	/**
	 * Method : insertParentPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 원글(부모글) 작성
	 */
	@Override
	public int insertParentPosts(PostsVO postsvo) {
		return dao.insertParentPosts(postsvo);
	}

	/**
	 * Method : insertChildPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 답글(자식글) 작성
	 */
	@Override
	public int insertChildPosts(PostsVO postsvo) {
		return dao.insertChildPosts(postsvo);
	}

	/**
	 * Method : getPostsDetail
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 게시글 상세보기
	 */
	@Override
	public PostsVO getPostsDetail(String postsno) {
		return dao.getPostsDetail(postsno);
	}

	/**
	 * Method : updatePosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsvo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updatePosts(Map<String, Object> map) {
		PostsVO postsvo = (PostsVO) map.get("postsvo");
		
		int postsCnt = dao.updatePosts(postsvo);
		
		IAttachmentsDao attadao = new AttachmentsDao();
		List<AttachmentsVO> attaList = (List<AttachmentsVO>) map.get("attavo");
		
		int attaCnt = 0;
		for(AttachmentsVO attavo : attaList){
			attaCnt += attadao.insertAtta(attavo);
		}
		
		return postsCnt + attaCnt;
	}

	/**
	 * Method : updateUse
	 * 작성자 : jerry
	 * 변경이력 :
	 * @param postsno
	 * @return
	 * Method 설명 : 게시글 삭제 구분 수정
	 */
	@Override
	public int updateUse(String postsno) {
		return dao.updateUse(postsno);
	}

	/**
	 * Method : maxPosts
	 * 작성자 : jerry
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 최신번호
	 */
	@Override
	public String maxPosts(String bid) {
		return dao.maxPosts(bid);
	}
	
}

