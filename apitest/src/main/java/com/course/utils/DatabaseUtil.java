package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author qym
 */
public class DatabaseUtil {
    public static SqlSession getSqlSession() throws IOException {
        //获取配置资源文件
        Reader reader = Resources.getResourceAsReader("dataBaseConfig.xml");
        System.out.println(reader);

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        //sqlSession执行配置文件中的sql语句sqlSession.select....
        SqlSession session = factory.openSession();
        return session;
    }
}
