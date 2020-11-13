package exam1.service;

import exam1.dao.MemberDao;
import exam1.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		memberDao = new MemberDao();
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);

	}

	public Member getMemberid(int id) {
		return memberDao.getMemberid(id);
	}

}
