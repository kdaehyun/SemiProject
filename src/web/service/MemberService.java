package web.service;

import web.dao.MemberDAO;
import web.util.ShopException;
import web.vo.MemberVO;

public class MemberService {
	
	MemberDAO dao;
	
	public MemberService() {
		 dao=new MemberDAO();
	}

	public void insertMember(MemberVO vo) throws ShopException {
		
		dao.insertMember(vo);
	}

	public String selectMember(MemberVO vo) throws ShopException {		
		return dao.selectMember(vo);
	}

	public void deleteMember(String id) throws ShopException {
		dao.deleteMember(id);		
	}
}




