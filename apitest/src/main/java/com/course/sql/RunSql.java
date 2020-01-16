package com.course.sql;

import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class RunSql {
    @Test(description = "执行SQL")
    public void runSql() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        int a = 407;
        System.out.println(session);
        int getUserInfoCase = session.delete("getUserInfoCase",407);
        session.commit();
        System.out.println(getUserInfoCase);
    }
}
