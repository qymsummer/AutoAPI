package com.course.sql;

import com.course.utils.DatabaseUtil;
import com.course.utils.TokenFile;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RunSqlT1 {
    public static void deleteUser(String Str) throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        int GetSysUser = session.delete("getSysUser",Str);
        session.commit();
        System.out.println("-------------");
        int num = 1;
        Assert.assertEquals(num,GetSysUser);
    }
    public static void deleteSysOrg(String Str) throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        int GetSysUser = session.delete("getSysUser",Str);
        session.commit();
        System.out.println("-------------");
        int num = 1;
        Assert.assertEquals(num,GetSysUser);
    }

}
