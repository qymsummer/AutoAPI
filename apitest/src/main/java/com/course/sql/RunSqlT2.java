package com.course.sql;

import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class RunSqlT2 {
    @Test(description = "执行SQL")
    public void runSql() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        int a = 23710;
        System.out.println(session);
        int GetSysOrganization = session.delete("GetSysOrganization",a);
        session.commit();
        System.out.println("-------------");
        System.out.println(GetSysOrganization);
    }
}
