package com.course.bvtcase.orgmanager;

import com.course.sql.RunPgSql;
import com.course.utils.TokenFile;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Description ApiAutoTest
 * @Date 2020/1/18 11:30
 * @Author qym
 */
public class DeletePgSqlOrg {
    @Test(groups = "DeletePgSqlOrg",description = "删除机构")
    public void deletePgSqOrg() throws IOException, InterruptedException {
        Thread.sleep(100);
        String str =  TokenFile.readFile("E:\\Data\\OrgID.txt");
        System.out.println(str);
        RunPgSql.deleteSysOrg(str);
        System.out.println("----");
    }
}
