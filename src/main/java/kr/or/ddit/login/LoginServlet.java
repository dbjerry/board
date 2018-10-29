package kr.or.ddit.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.sha.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

@WebServlet(urlPatterns={"/login", "/logout"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO uservo = new UserVO();
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("pass");
		String rememberMe = request.getParameter("remember-me");
		
		if(rememberMe == null){
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
				
				// cookie 이름이 remember, userId 일 경우 maxage를
				// -1 설정하여 쿠키를 유효하지 않도록 설정
				if(cookie.getName().equals("remember") || cookie.getName().equals("userId")){
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					System.out.println(cookie.getMaxAge());
				}
			}
		} else {
			// response 객체에 쿠키를 저장
			Cookie cookie = new Cookie("remember", "Y");
			Cookie userIdCookie = new Cookie("userId", userId);
			// response 객체에 쿠키를 저장
    		
			response.addCookie(userIdCookie);
			response.addCookie(cookie);
		}
		
		IUserService userService = new UserService();
		IBoardService boardService = new BoardService();
		
		UserVO getUser = userService.selectUser(userId);
		List<BoardVO> boardList = boardService.selectAllBoard();
		
		String encryptPass = KISA_SHA256.encrypt(password);
		
		if(getUser != null && getUser.authPass(encryptPass)){
			
			request.getSession().setAttribute("S_USER", getUser);
			getServletContext().setAttribute("boardList", boardList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		
		} else {
			response.sendRedirect("/login/login.jsp");
		}
		
	}

}

