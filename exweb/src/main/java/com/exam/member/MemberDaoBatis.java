package com.exam.member;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDaoBatis implements MemberDao {

	SqlSessionFactory sqlSessionFactory;	//분리 후 필드로
	{
		// 컨트롤 + 쉬프트 + O : 한번에 임포트 받기
		try {
			//마이바티스 전체 설정파일 위치(클래스패스 기준)
			String resource = "batis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//설정파일의 내용대로 sqlSessionFactory(마이바티스본체)
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		  }
		
	}
	
	@Override
	public List<MemberVo> selectMemberList() {
		
		List<MemberVo> list = null;	//new ArrayList<MemberVo>();  //초기값 선언
		
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// 실행할 SQL문과 동일한 이름의 메서드를 사용하여 SQL문 실행
			// SELECT결과가 1행인 경우 selectOne, 2행이상인 경우 selectList 메서드 사용
			// 첫번째 인자로 실행할 SQL문의 고유한 이름을 전달
			// 두번째 인자로 SQL문 실행시 필요한 데이터(담은 객체)를 전달
			list = session.selectList("com.exam.member.MemberDao.selectMemberList");
			}
		
		return list;
	}

	@Override
	public int insertMember(MemberVo vo) {
		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// 실행할 SQL문과 동일한 이름의 메서드를 사용하여 SQL문 실행
			// SELECT결과가 1행인 경우 selectOne, 2행이상인 경우 selectList 메서드 사용
			// 첫번째 인자로 실행할 SQL문의 고유한 이름을 전달
			// 두번째 인자로 SQL문 실행시 필요한 데이터(담은 객체)를 전달
			num = session.insert("com.exam.member.MemberDao.insertMember", vo);
			session.commit();	//INSERT, UPDATE, DELETE 후에는 COMMIT 필요
			}
		return num;
	}

	// 삭제버튼을 클릭하면, 삭제가 되도록 MemberDaoBatis 클래스와 MemberMapper.xml 파일을 변경하세요.
	@Override
	public int deleteMember(String memId) {
		int nums = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// 실행할 SQL문과 동일한 이름의 메서드를 사용하여 SQL문 실행
			// SELECT결과가 1행인 경우 selectOne, 2행이상인 경우 selectList 메서드 사용
			// 첫번째 인자로 실행할 SQL문의 고유한 이름을 전달
			// 두번째 인자로 SQL문 실행시 필요한 데이터(담은 객체)를 전달
			nums = session.delete("com.exam.member.MemberDao.deleteMember", memId);
			session.commit();	//INSERT, UPDATE, DELETE 후에는 COMMIT 필요
			}
		return nums;
	}

	
	
}
