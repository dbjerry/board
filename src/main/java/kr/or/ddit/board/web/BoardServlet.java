package kr.or.ddit.board.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

@WebServlet(urlPatterns={"/boardUpdate", "/boardInsert"})
public class BoardServlet extends HttpServlet {

	IBoardService service = new BoardService();
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI();
		
		if(uri.equals("/boardInsert")){
			boardInsert(request, response);
			
		}else if(uri.equals("/boardUpdate")){
			boardUpdate(request, response);
			
		}
		
	}

	/**
	 * Method : boardUpdate
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * Method 설명 : 게시판 수정
	 */
	private void boardUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		
		BoardVO boardvo = new BoardVO();
		
		String board_id = request.getParameter("board_id");
		String board_name = request.getParameter("board_name");
		String board_yn = request.getParameter("board_yn");

		System.out.println("board_id : " + board_id);
		System.out.println("board_name : " + board_name);
		System.out.println("board_yn : " + board_yn);
		
		boardvo.setBoard_id(board_id);
		boardvo.setBoard_name(board_name);
		boardvo.setBoard_yn(board_yn);
		
		int cnt = service.updateBoard(boardvo);
		
		if(cnt > 0){
			goMain(response);
			
		}else{
			response.sendRedirect("/board/addBoard.jsp");
			
		}
		
	}

	/**
	 * Method : boardInsert
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param request
	 * @param response
	 * @throws IOException
	 * Method 설명 : 게시판 생성
	 */
	private void boardInsert(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		UserVO uservo = (UserVO)request.getSession().getAttribute("S_USER");
		System.out.println(uservo);
		
		String userId = uservo.getUserId();
		String board_name = request.getParameter("board_name");
		String board_yn = request.getParameter("board_yn");
		
		BoardVO boardvo = new BoardVO();
		
		boardvo.setBoard_name(board_name);
		boardvo.setUserId(userId);
		boardvo.setBoard_yn(board_yn);
		
		int cnt = service.insertBoard(boardvo);
		
		if(cnt > 0){
			goMain(response);
			
		}else{
			response.sendRedirect("/board/addBoard.jsp");
			
		}
		
	}
	
	
	/**
	 * Method : goMain
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param response
	 * @throws IOException
	 * Method 설명 : insert, update 성공시 addBoard.jsp로 이동 및 left.jsp 새로고침
	 */
	private void goMain(HttpServletResponse response) throws IOException {
		IBoardService boardService = new BoardService();
		List<BoardVO> boardList = boardService.selectAllBoard();
		getServletContext().setAttribute("boardList", boardList);
		
		response.sendRedirect("/board/addBoard.jsp");
		
	}

}

