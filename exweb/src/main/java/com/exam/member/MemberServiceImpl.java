package com.exam.member;

import java.util.List;

//싱글톤(Singleton) : 애플리케이션 전체에서 인스턴스를 1개만 생성하여 사용하는 객체

public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao = MemberDaoBatis.getInstance();
	
	private MemberServiceImpl() { }	//외부에서 생성자 호출 금지
	//클래스가 처름 로드될때, 객체를 생성하여 저장
	private static MemberService memberService = new MemberServiceImpl();
	public static MemberService getInstacne() {
		return memberService;	//생성해놓은 객체를 반환
	}
	
	//멤버 리스트
	@Override
	public List<MemberVo> selectMemberList() {
		return memberDao.selectMemberList();
	}

	//멤버 추가
	@Override
	public int insertMember(MemberVo vo) {
		return memberDao.insertMember(vo);
	}

	//멤버 삭제
	@Override
	public int deleteMember(String memId) {
		return memberDao.deleteMember(memId);
	}

	//멤버 수정
	@Override
	public int updateMember(MemberVo vo) {
		return memberDao.updateMember(vo);
	}

	//멤버 검색
	@Override
	public MemberVo selectMember(String memId) {
		return memberDao.selectMember(memId);
	}

	//멤버 로그인
	@Override
	public MemberVo selectLogin(MemberVo vo) {
		return memberDao.selectLogin(vo);
	}

}
