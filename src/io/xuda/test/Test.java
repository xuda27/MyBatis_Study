package io.xuda.test;

import io.xuda.beans.Users;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {

	public static void main(String[] args) {
		//mybatis的配置文件
		String resource = "conf.xml";
		//使用类加载器加载mybatis的配置文件 (它也加载关联的映射文件)
//		InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
		
		//构建sqlSession的工厂 
//		SqlSessionFactory sessionFactory =new SqlSessionFactoryBuilder().build(is);
		Reader reader=null;
		try {
			// 读取conf.xml配置文件
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sessionFactory =new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession session = sessionFactory.openSession();
		
		String statement = "io.xuda.mapping.usersMapper.getUsers"; // 映射sql的标识字符串
		
		
		Users user =null;
		try {
			user = session.selectOne(statement,1);
		} finally{
			session.close();
		}
		
		System.out.println(user);
		
	}

}
