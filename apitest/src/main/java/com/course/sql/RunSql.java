package com.course.sql;

import com.course.utils.DatabaseUtil;
import com.course.utils.TokenFile;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author qym
 */
public class RunSql {
    public static void deleteUser(String str) throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        int getSysUser = session.delete("sys_user",str);
        session.commit();
        System.out.println("-------------");
        int num = 1;
        Assert.assertEquals(num,getSysUser);
    }
    public static void deleteSysOrg(String str) throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        int getSysUser = session.delete("sys_organization",str);
        session.commit();
        System.out.println("-------------");
        int num = 1;
        Assert.assertEquals(num,getSysUser);
    }

}
