package com.course.bvtcase.usermanagement;

import com.course.sql.RunSqlT1;
import com.course.utils.TokenFile;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/16 15:55
 */
public class DeleteUser {

    @Test(groups = "DeleteUser",description = "删除用户")
    public void addUser() throws IOException {
        String userid =  TokenFile.readFile("E:\\Data\\userId.txt");
        System.out.println(userid);
        RunSqlT1.deleteUser(userid);
    }
}
