package com.course.xxgx;

import com.course.config.TestConfig;
import com.course.config.TestConfigXXGX;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFileH5;
import com.course.utils.ConfigFileXXGX;
import com.course.utils.H5TokenFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GetXxgxData {
    @BeforeTest(
            description = "获取到查看产品的支行"
    )
    public void beforeTest() {
        TestConfigXXGX.finduri = ConfigFileXXGX.getUrl(InterfaceName.FIND);
        System.out.println(TestConfigXXGX.finduri);
    }
    @AfterTest
    public void afterTest(){

    }
    @Test(groups = "lookProduct",dependsOnGroups = "loginCaseDbXXGX",description = "查看产品详情测试")
    public void lookProduct() throws Exception {
        List<String> list = getList();
        for (String temp : list){
            URIBuilder builder = new URIBuilder(TestConfigXXGX.finduri);
            builder.addParameter("keywords",temp);
            builder.addParameter("page","1");
            builder.addParameter("count","5");
            HttpGet httpGet = new HttpGet(builder.build());
            String name="jwtToken";
            String value = TokenFile.readFile();
            httpGet.setHeader(name,value);
            HttpResponse response =  TestConfig.client.execute (httpGet);
            String result;
            result = EntityUtils.toString (response.getEntity(),"utf-8");
            System.out.println(result);
        }
    }

    private List getList() {
        List<String> list = new ArrayList<String>();
        list.add("91331004MA28GWGR5");
        list.add("91331004558607389");
        list.add("91331004558607266");
        list.add("9133100455860433X");
        list.add("91331003692369174");
        list.add("91331004558610502");
        list.add("91331082566984766");
        list.add("91331082667138841");
        list.add("91331002564403905");
        list.add("91331004558608031");
        list.add("91331081679598722");
        list.add("91331022798555397");
        list.add("91331081587769109");
        list.add("91331000MA2AKD522");
        list.add("91331003597202659");
        list.add("91331024573982240");
        list.add("91331002551793713");
        list.add("91331004558612241");
        list.add("91331021678442259");
        list.add("91331023684515194");
        list.add("91331000677245691");
        list.add("91331081558649810");
        list.add("91331081329832494");
        list.add("91331021344058806");
        list.add("91331002MA28G4K97");
        list.add("91331021327868186");
        list.add("91331024MA2AM0WC1");
        list.add("91331002MA28HBRE7");
        list.add("91331002307751036");
        list.add("91331082MA28H6XE1");
        list.add("91331004313551882");
        list.add("91331082337034820");
        list.add("91331081327859810");
        list.add("91331002MA28GKFPX");
        list.add("91331003MA28GJXT4");
        list.add("91331082MA28GLGX4");
        list.add("91331082MA2AM1F59");
        list.add("91331082327859888");
        list.add("91331081323435041");
        list.add("91331081MA28G4065");
        list.add("91331081323435025");
        list.add("91331082MA2AM1FM8");
        list.add("91331082MA28H7KN5");
        list.add("91331004307685382");
        list.add("91331023MA29X47T1");
        list.add("91331081327862139");
        list.add("91331003307641793");
        list.add("91331003MA28G43M5");
        list.add("91331081MA28G2R88");
        list.add("91331022327918198");
        list.add("91331022MA2ALXYBO");
        list.add("91331004MA28G48B7");
        list.add("91331024MA28GK835");
        list.add("91331004344001525");
        list.add("91331024323495570");
        list.add("91331002MA28GKFPX");
        list.add("91331081323435076");
        list.add("91331081MA2DTDB30");
        list.add("91331021MA2DTD858");
        list.add("91331082MA2DTEP83");
        list.add("91331081MA2DTD874");
        list.add("91331003MA2AMR8K8");
        list.add("91331003MA28G5762");
        list.add("91331022313550812");
        list.add("91331081313534601");
        list.add("91331081095657821");
        list.add("91331003329905243");
        list.add("91331021336982693");
        list.add("91331082080583444");
        list.add("91331002MA28HCY34");
        list.add("91331082329891531");
        list.add("91331081095655770");
        list.add("91331081062045677");
        list.add("91331082313534062");
        list.add("91331081095655658");
        list.add("91331022313550556");
        list.add("91331021MA2ANBPT1");
        list.add("91331082329891777");
        list.add("91331082313559200");
        list.add("91331021336982378");
        list.add("91331023MA28G8C89");
        list.add("9133100407972887X");
        list.add("91331024MA28G5GQ3");
        list.add("91331082MA28H7H91");
        list.add("91331002MA28G5439");
        list.add("91331021063181195");
        list.add("9133108106058372X");
        list.add("91331023MA28GEUD0");
        list.add("91331023MA28HBJF4");
        list.add("91331081MA2AN5N80");
        return list;
    }
}

