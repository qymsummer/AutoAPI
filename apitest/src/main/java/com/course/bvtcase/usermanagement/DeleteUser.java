package com.course.bvtcase.usermanagement;

import com.course.sql.RunSql;
import com.course.utils.TokenFile;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/16 15:55
 * @author qym
 */
public class DeleteUser {

    @Test(groups = "DeleteUser",description = "删除用户")
    public void deleteUser() throws IOException, InterruptedException {
        Thread.sleep(100);
        String userId =  TokenFile.readFile("E:\\Data\\userId.txt");
        System.out.println(userId);
        RunSql.deleteUser(userId);
        System.out.println("---");
    }

}
