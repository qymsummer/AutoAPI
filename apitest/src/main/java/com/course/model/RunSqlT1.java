package com.course.model;

import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class RunSqlT1 {
    @Test(description = "执行SQL")
    public void runSql() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        int a = 406;
        System.out.println(session);
        int GetSysUser = session.delete("getSysUser",a);
        session.commit();
        System.out.println("-------------");
        System.out.println(GetSysUser);
    }
}
