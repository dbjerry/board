package kr.or.ddit.user.service;

import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVO;

public class UserService implements IUserService{

	UserDao dao = new UserDao();
	
	/**
	 * Method : selectUser
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 회원 검색(인자 : String)
	 */
	@Override
	public UserVO selectUser(String userId) {
		return dao.selectUser(userId);
	}

}

