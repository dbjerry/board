package kr.or.ddit.comm.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.model.CommVO;
import kr.or.ddit.comm.service.CommService;
import kr.or.ddit.comm.service.ICommService;

@WebServlet(urlPatterns={"/addComm", "/updateComm"})
public class CommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ICommService service = new CommService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if(uri.equals("/updateComm")){
			updateComm(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		
		if(uri.equals("/addComm")){
			addComm(request, response);
		}
		
	}
	

	/**
	 * Method : updateComm
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 댓글 삭제 구분
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateComm(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		String board_id = request.getParameter("board_id");
		String posts_no = request.getParameter("posts_no");
		String comm_id = request.getParameter("comm_id");
		
		service.updateComm(comm_id);
		
		response.sendRedirect("/postsDetail?board_id=" + board_id + "&posts_no=" + posts_no);
		
	}

	/**
	 * Method : addComm
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * Method 설명 : 댓글 추가
	 * @throws IOException 
	 */
	private void addComm(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		
		String posts_no = request.getParameter("posts_no");
		String comm_cnt = request.getParameter("comm_cnt");
		String userid = request.getParameter("userid");
		
		CommVO commvo = new CommVO();
		commvo.setPosts_no(posts_no);
		commvo.setComm_cnt(comm_cnt);
		commvo.setUserid(userid);
		
		service.insertComm(commvo);
		String board_id = request.getParameter("board_id");
		
		response.sendRedirect("/postsDetail?board_id=" + board_id + "&posts_no=" + posts_no);
		
	}

}

