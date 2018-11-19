package kr.or.ddit.posts.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.attachments.service.AttachmentsService;
import kr.or.ddit.attachments.service.IAttachmentsService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.comm.model.CommVO;
import kr.or.ddit.comm.service.CommService;
import kr.or.ddit.comm.service.ICommService;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.posts.service.IPostsService;
import kr.or.ddit.posts.service.PostsService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.StringUtil;
import kr.or.ddit.util.model.PageVO;

@MultipartConfig(maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5)
@WebServlet(urlPatterns={"/postsPageList", "/addPostsForm", "/postsDetail", "/postsUpdateForm", "/postsUpdateUse",
						"/addReplyPosts", "/addReplyForm", "/updateAttachFile"})
public class PostsServlet extends HttpServlet {
	IPostsService postsService = new PostsService();
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if(uri.equals("/postsPageList")){
			postsPageList(request, response);
		}else if(uri.equals("/postsDetail")){
			postsDetail(request, response);
		}else if(uri.equals("/postsUpdateForm")){
			postsUpdateForm(request, response);
		}
		
	}
	
	
	/**
	 * Method : postsUpdateForm
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 게시글 수정 Form
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void postsUpdateForm(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String posts_no = request.getParameter("posts_no");
		
		PostsVO postsvo = postsService.getPostsDetail(posts_no);
		
		IAttachmentsService attaService = new AttachmentsService();
		List<AttachmentsVO> update_attaList = attaService.selectAtta(posts_no);
		
		request.setAttribute("r_postsvo", postsvo);
		request.setAttribute("update_attaList", update_attaList);
		request.getRequestDispatcher("/posts/postsUpdateForm.jsp").forward(request, response);
		
	}


	/**
	 * Method : postsDetail
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 게시글 상세보기
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void postsDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String posts_no = request.getParameter("posts_no");
		//String board_id = request.getParameter("board_id");
		PostsVO postsvo = postsService.getPostsDetail(posts_no);
		
		request.setAttribute("postsvo", postsvo);
		
		// 댓글 조회
		ICommService service = new CommService();
		
		List<CommVO> commList = service.selectComm(posts_no);
		
		request.setAttribute("commList", commList);
		//----------------------------------------
		
		
		// 첨부파일 조회
		IAttachmentsService attaService = new AttachmentsService();
		
		List<AttachmentsVO> attaList = attaService.selectAtta(posts_no);
		
		request.setAttribute("attaList", attaList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/posts/postsDetail.jsp");
		rd.forward(request, response);
		
	}

	
	/**
	 * Method : postsPageList
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 게시글 리스트(페이징처리) 조회
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void postsPageList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		PageVO pagevo = new PageVO();
		BoardVO boardvo = new BoardVO();
		
		boardvo.setBoard_id(request.getParameter("board_id"));
		boardvo.setBoard_name(request.getParameter("board_name"));
		pagevo.setPage(Integer.parseInt(request.getParameter("page")));
		pagevo.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardvo", boardvo);
		map.put("pagevo", pagevo);
		
		Map<String, Object> resultMap = postsService.postsPageList(map);
		
		List<PostsVO> postsList = (List<PostsVO>) resultMap.get("postsList");
		
		int postsCnt = (int) resultMap.get("postsCnt");
		
		
		
		
		//request.setAttribute("pagevo", pagevo);
		request.setAttribute("boardvo", boardvo);
		request.setAttribute("postsList", postsList);
		request.setAttribute("postsCnt", postsCnt);
		request.setAttribute("page", request.getParameter("page"));
		
		RequestDispatcher rd = request.getRequestDispatcher("/posts/posts.jsp");
		rd.forward(request, response);
		
	}

	
	/* doPost */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if(uri.equals("/addPostsForm")){
			addPostsForm(request, response);
		}else if(uri.equals("/postsUpdateForm")){
			postsUpdate(request, response);
		}else if(uri.equals("/postsUpdateUse")){
			updateUse(request, response);
		}else if(uri.equals("/addReplyPosts")){
			insertChildPosts(request, response);
		}else if(uri.equals("/updateAttachFile")){
			updateAttachFile(request, response);
		}
		
	}
	
	
	/**
	 * Method : updateAttachFile
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 :
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateAttachFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		String attano = request.getParameter("attaFileName");

		IAttachmentsService attaService = new AttachmentsService();
		attaService.deleteAtta(attano);

		request.getRequestDispatcher("/posts/postsUpdateForm.jsp").forward(request, response);

	}


	/**
	 * Method : insertChildPosts
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 답글등록
	 * @throws IOException 
	 */
	private void insertChildPosts(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		
		UserVO uservo = new UserVO();
		uservo = (UserVO) request.getSession().getAttribute("S_USER");
		
		String userId = uservo.getUserId();
		String board_id = request.getParameter("board_id");
		String posts_title = request.getParameter("posts_title");
		String posts_cnt = request.getParameter("smarteditor");
		String posts_hno = request.getParameter("posts_no");
		
		PostsVO postsvo = new PostsVO();
		postsvo.setBoard_id(board_id);
		postsvo.setPosts_title(posts_title);
		postsvo.setPosts_cnt(posts_cnt);
		postsvo.setUserid(userId);
		postsvo.setPosts_hno(posts_hno);
		
		postsService.insertChildPosts(postsvo);
		
		response.sendRedirect("/postsPageList?page=1&pageSize=10&board_id="+board_id);
		
	}


	/**
	 * Method : updateUse
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 게시글 삭제 구분 수정
	 * @throws IOException 
	 */
	private void updateUse(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		
		String posts_no = request.getParameter("posts_no");
		
		int updateUse = postsService.updateUse(posts_no);
		
		String board_id = request.getParameter("board_id");
		
		response.sendRedirect("/postsPageList?page=1&pageSize=10&board_id=" + board_id);
		
	}


	/**
	 * Method : postsUpdate
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 게시글 수정
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void postsUpdate(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<AttachmentsVO> attaList = new ArrayList<AttachmentsVO>();
		
		String posts_no = request.getParameter("posts_no");
		String posts_cnt = request.getParameter("smarteditor");
		System.out.println("PostsServlet line 293) posts_cnt : " + posts_cnt);

		String board_id = request.getParameter("board_id");
		
		PostsVO postsvo = new PostsVO();
		postsvo.setPosts_no(posts_no);
		postsvo.setPosts_cnt(posts_cnt);
		
		for (int i = 0; i < 5; i++) {
			Part attachments = request.getPart("files"+i);
			System.out.println("PostsServlet line 303) attachments : " + attachments);
			
			if(attachments != null){
				
				String contentDisposition = attachments.getHeader("Content-disposition");
				
				String fileName = StringUtil.getFileName(contentDisposition);
				// ㄴ공백인지 아닌지 확인 후 공백이 아닌 놈만 db에 저장
				
				String path = getServletContext().getRealPath("/attachfile");
				
				String attachfile = null;
				if(!(fileName.equals(""))){
					attachments.write(path + File.separator + fileName);
					attachments.delete();
				
					attachfile = "/attachfile/" + fileName;
					
				}else{
					continue;
					
				}
			
				AttachmentsVO attavo = new AttachmentsVO();
				
				
				attavo.setAtta_file(attachfile);
				attavo.setPosts_no(posts_no);
				
				attaList.add(attavo);
			}
		}
		
		map.put("postsvo", postsvo);
		map.put("attavo", attaList);
		
		int postsUpdate = postsService.updatePosts(map);
		
		if(postsUpdate > 0){
			response.sendRedirect("/postsPageList?page=1&pageSize=10&board_id="+board_id);
		
		}else{
			response.sendRedirect("/postsUpdateForm?posts_no="+posts_no);
			
		}

	}


	/**
	 * Method : addPostsForm
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 게시글 등록
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void addPostsForm(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		UserVO uservo = new UserVO();
		uservo = (UserVO) request.getSession().getAttribute("S_USER");
		
		String userId = uservo.getUserId();
		String board_id = request.getParameter("board_id");
		String posts_title = request.getParameter("posts_title");
		String posts_cnt = request.getParameter("smarteditor");
		
		PostsVO postsvo = new PostsVO();
		postsvo.setBoard_id(board_id);
		postsvo.setUserid(userId);
		postsvo.setPosts_title(posts_title);
		postsvo.setPosts_cnt(posts_cnt);
		
		int insertCnt = postsService.insertParentPosts(postsvo);
		
		if(insertCnt > 0) {

			for (int i = 1; i <= 5; i++) {
			
				Part attachments = request.getPart("attachments"+i);
			
				String contentDisposition = attachments.getHeader("Content-disposition");
				
				String fileName = StringUtil.getFileName(contentDisposition);
				// ㄴ공백인지 아닌지 확인 후 공백이 아닌 놈만 db에 저장
				
				String path = getServletContext().getRealPath("/attachfile");
				
				String attachfile = null;
				if(!(fileName.equals(""))){
					attachments.write(path + File.separator + fileName);
					attachments.delete();
				
					attachfile = "/attachfile/" + fileName;
					
				}else{
					continue;
					
				}
			
				AttachmentsVO attavo = new AttachmentsVO();
				
				String maxPosts = postsService.maxPosts(board_id);
				
				attavo.setAtta_file(attachfile);
	
				attavo.setPosts_no(maxPosts);
				
				IAttachmentsService attaService = new AttachmentsService();
				
				int insertAtta = attaService.insertAtta(attavo);
				
			}
			response.sendRedirect("/postsPageList?page=1&pageSize=10&board_id="+board_id);
		
		} else {
			response.sendRedirect("/postsPageList?page=1&pageSize=10&board_id="+board_id);

		}
	}
}

