package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    //执行这个用例，前提需要登录成功！
    //所以需要依赖登录成功那个组
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void addUser() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        System.out.println(session);
        AddUserCase addUserCase = session.selectOne("addUserCase", 1);
        System.out.println("测试数据为："+ addUserCase.toString());
        System.out.println( "addUser的uri为：" + TestConfig.addUserUrl);
        //发请求，获取结果
        String result = getResult(addUserCase);
        //验证返回结果
        //session.commit();
        /*
        getResult调用接口，实际上是又开了一个进程，跟测试用例addUser()代码是并行运行的，
        这个时候就需要让测试代码休息一下，等待接口调用完毕后，再去查数据库的结果，否则可能查出来user为null
         */
        Thread.sleep(2000);

        User user = session.selectOne("addUser", addUserCase); //sql：查看新增加的user
        System.out.println("请求接口后，user表中新增的user为：" + user.toString());

        //处理结果，判断返回结果是否符合预期
        Assert.assertEquals(addUserCase.getExpected(), result);
    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("userName", addUserCase.getUserName());
        param.put("password", addUserCase.getPassword());
        param.put("sex", addUserCase.getSex());
        param.put("age", addUserCase.getAge());
        param.put("permission", addUserCase.getPermission());
        param.put("isDelete", addUserCase.getIsDelete());
        //设置头信息
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result; //存放返回结果
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("请求响应结果为：" + result);
        return result;
    }
}
