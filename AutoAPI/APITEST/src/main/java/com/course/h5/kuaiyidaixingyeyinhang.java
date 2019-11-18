package com.course.h5;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFileH5;
import com.course.utils.DataIdFile;
import com.course.utils.H5TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class kuaiyidaixingyeyinhang {
    @BeforeTest(
            description = "获取到查看产品的支行"
    )
    public void beforeTest() {

        TestConfig.h5Loign = ConfigFileH5.getUrl(InterfaceName.H5LOGIN);
    }
    @AfterTest
    public void afterTest(){

    }
    @Test(description = "查看产品详情测试")
    public void lookProduct() throws Exception {
        List<String> list = getList();


        for (String temp : list){
            URIBuilder builder = new URIBuilder(TestConfig.h5Loign);
            builder.addParameter("bankId","10251");
            builder.addParameter("districtId",temp);
            HttpGet httpGet = new HttpGet(builder.build());
            String name="gxdjkey";
            String value = H5TokenFile.readFile();
            httpGet.setHeader(name,value);
            HttpResponse response =  TestConfig.client.execute (httpGet);
            String result;
            result = EntityUtils.toString (response.getEntity(),"utf-8");
            System.out.println("银行名称:兴业银行"+" | "+"产品名称:快易贷"+" | "+"城市ID:"+temp+"\n"+"返回结果:"+result+"\n");
        }
    }

    private List getList() {
        List<String> list = new ArrayList<String>();
        list.add("330102");
        list.add("330103");
        list.add("330104");
        list.add("330105");
        list.add("330106");
        list.add("330108");
        list.add("330109");
        list.add("330110");
        list.add("330111");
        list.add("330112");
        list.add("330122");
        list.add("330127");
        list.add("330151");
        list.add("330155");
        list.add("330182");
        list.add("330199");
        list.add("330302");
        list.add("330303");
        list.add("330304");
        list.add("330305");
        list.add("330324");
        list.add("330326");
        list.add("330327");
        list.add("330328");
        list.add("330329");
        list.add("330353");
        list.add("330354");
        list.add("330355");
        list.add("330371");
        list.add("330381");
        list.add("330382");
        list.add("330399");
        list.add("330402");
        list.add("330411");
        list.add("330421");
        list.add("330424");
        list.add("330451");
        list.add("330452");
        list.add("330481");
        list.add("330482");
        list.add("330483");
        list.add("330499");
        list.add("330502");
        list.add("330503");
        list.add("330521");
        list.add("330522");
        list.add("330523");
        list.add("330552");
        list.add("330599");
        list.add("330602");
        list.add("330603");
        list.add("330604");
        list.add("330624");
        list.add("330652");
        list.add("330681");
        list.add("330683");
        list.add("330699");
        list.add("330702");
        list.add("330703");
        list.add("330723");
        list.add("330726");
        list.add("330727");
        list.add("330751");
        list.add("330752");
        list.add("330753");
        list.add("330781");
        list.add("330782");
        list.add("330783");
        list.add("330784");
        list.add("330799");
        list.add("330802");
        list.add("330803");
        list.add("330822");
        list.add("330824");
        list.add("330825");
        list.add("330851");
        list.add("330852");
        list.add("330881");
        list.add("330899");
        list.add("331002");
        list.add("331003");
        list.add("331004");
        list.add("331022");
        list.add("331023");
        list.add("331024");
        list.add("331051");
        list.add("331052");
        list.add("331081");
        list.add("331082");
        list.add("331083");
        list.add("331099");
        list.add("331102");
        list.add("331121");
        list.add("331122");
        list.add("331123");
        list.add("331124");
        list.add("331125");
        list.add("331126");
        list.add("331127");
        list.add("331151");
        list.add("331181");
        list.add("331199");
        return list;
    }
}
