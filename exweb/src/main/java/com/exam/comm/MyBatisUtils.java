package com.exam.comm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	
	private static SqlSessionFactory sqlSessionFactory;	//분리 후 필드로
	
	static {
		// 컨트롤 + 쉬프트 + O : 한번에 임포트 받기
		try {
			//마이바티스 전체 설정파일 위치(클래스패스 기준)
			String resource = "batis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//설정파일의 내용대로 sqlSessionFactory(마이바티스본체)
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
