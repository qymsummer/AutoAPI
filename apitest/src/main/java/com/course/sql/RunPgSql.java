package com.course.sql;

import com.course.utils.DatabasePgSqlUtil;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Description ApiAutoTest
 * @Date 2020/1/18 11:06
 * @Author qym
 */

public class RunPgSql {

        public static void deleteSysOrg(String str) throws IOException {
            SqlSession session = DatabasePgSqlUtil.getSqlSession();
            int getSysOrganization = session.delete("sys_organization", str);
            session.commit();
            System.out.println("-------------");
            int num = 1;
            Assert.assertEquals(num, getSysOrganization);
        }
}
