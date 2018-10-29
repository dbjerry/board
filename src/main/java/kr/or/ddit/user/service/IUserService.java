package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVO;

	public interface IUserService {
	
	/**
	 * Method : selectUser
	 * 작성자 : 김지태
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 회원 검색(인자 : String)
	 */
	public UserVO selectUser(String userId);
	
}

	