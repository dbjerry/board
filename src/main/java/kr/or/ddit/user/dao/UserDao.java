package kr.or.ddit.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.SqlFactoryBuilder;
import kr.or.ddit.user.model.UserVO;

public class UserDao implements IUserDao{

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
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		UserVO selectUser = session.selectOne("user.selectUser", userId);
		
		session.close();
		
		return selectUser;
	}

}
