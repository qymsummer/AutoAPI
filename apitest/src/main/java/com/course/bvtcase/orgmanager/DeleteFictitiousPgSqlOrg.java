package com.course.bvtcase.orgmanager;

import com.course.sql.RunPgSql;
import com.course.sql.RunSql;
import com.course.utils.TokenFile;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Description ApiAutoTest
 * @Date 2020/1/18 11:23
 * @Author qym
 */
public class DeleteFictitiousPgSqlOrg {
    @Test(groups = "DeleteFictitiousPgSqlOrg",description = "删除虚拟机构")
    public void deleteFictitiousPgSqOrg() throws IOException, InterruptedException {
        Thread.sleep(100);
        String str =  TokenFile.readFile("E:\\Data\\FictitiousorgId.txt");
        System.out.println(str);
        RunPgSql.deleteSysOrg(str);
        System.out.println("----");
    }

}
