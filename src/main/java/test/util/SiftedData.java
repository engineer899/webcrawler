package test.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author 张伟
 * @date 2019/9/17 11:23
 */
public class SiftedData {

    //筛选各地数据
    public  static JsonArray selectdata(JsonArray jsonArray,String environment) {
        JsonArray array = new JsonArray();
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonobject = jsonElement.getAsJsonObject();
            String xzqhdm = "";
            String zs = "";
            String scdz = "";
            String scjydz = "";
            try {
                xzqhdm = jsonobject.get("xzqhdm").getAsString();
            } catch (NullPointerException e) {
            }
            try {
                zs = jsonobject.get("zs").getAsString();
            } catch (NullPointerException e) {
            }
            try {
                scdz = jsonobject.get("scdz").getAsString();
            } catch (NullPointerException e) {
            }
            try {
                scjydz = jsonobject.get("scjydz").getAsString();
            } catch (NullPointerException e) {
            }
            String qymc = jsonobject.get("qymc").getAsString();
            String fzjgmc = jsonobject.get("fzjgmc").getAsString();
            if(environment.equals("江西省")){
                array=jsonArray;
            } else if (environment.equals("江西省宜春市")) {
                if (xzqhdm.contains("360900")
                        || xzqhdm.contains("360901") || xzqhdm.contains("360902") || xzqhdm.contains("360921")
                        || xzqhdm.contains("360922") || xzqhdm.contains("360923") || xzqhdm.contains("360924")
                        || xzqhdm.contains("360925") || xzqhdm.contains("360926") || xzqhdm.contains("360981")
                        || xzqhdm.contains("360982") || xzqhdm.contains("360983")
                        || qymc.contains("宜春")
//                        || qymc.contains("袁州区") || qymc.contains("宜春市奉新县") || qymc.contains("宜春市万载县")
//                        || qymc.contains("宜春市上高") || qymc.contains("宜春市宜丰") || qymc.contains("宜春市靖安") || qymc.contains("宜春市铜鼓")
//                        || qymc.contains("宜春市丰城") || qymc.contains("宜春市樟树") || qymc.contains("宜春市高安")
                        || fzjgmc.contains("宜春")
//                        || fzjgmc.contains("袁州区") || fzjgmc.contains("宜春市奉新") || fzjgmc.contains("宜春市万载")
//                        || fzjgmc.contains("宜春市上高") || fzjgmc.contains("宜春市宜丰") || fzjgmc.contains("宜春市靖安") || fzjgmc.contains("铜鼓")
//                        || fzjgmc.contains("宜春市丰城") || fzjgmc.contains("宜春市樟树") || fzjgmc.contains("宜春市高安")
                        || zs.contains("宜春")
//                        || zs.contains("袁州区") || zs.contains("宜春市奉新") || zs.contains("宜春市万载")
//                        || zs.contains("宜春市上高") || zs.contains("宜春市宜丰") || zs.contains("宜春市靖安") || zs.contains("宜春市铜鼓")
//                        || zs.contains("宜春市丰城") || zs.contains("宜春市樟树") || zs.contains("宜春市高安")
                        || scdz.contains("宜春")
//                        || scdz.contains("袁州区") || scdz.contains("宜春市奉新") || scdz.contains("宜春市万载")
//                        || scdz.contains("宜春市上高") || scdz.contains("宜春市宜丰") || scdz.contains("宜春市靖安") || scdz.contains("宜春市铜鼓")
//                        || scdz.contains("宜春市丰城") || scdz.contains("宜春市樟树") || scdz.contains("宜春市高安")
                        || scjydz.contains("宜春")
//                        || scjydz.contains("袁州区") || scjydz.contains("宜春市奉新") || scjydz.contains("宜春市万载")
//                        || scjydz.contains("宜春市上高") || scjydz.contains("宜春市宜丰") || scjydz.contains("宜春市靖安") || scjydz.contains("宜春市铜鼓")
//                        || scjydz.contains("宜春市丰城") || scjydz.contains("宜春市樟树") || scjydz.contains("宜春市高安")
                ) {
                    array.add(jsonobject);
                }
            } else if(environment.equals("江西省抚州市崇仁县")){
                if (xzqhdm.contains("361024")
                        || qymc.contains("崇仁县")
//                        || qymc.contains("崇仁县巴山镇")  || qymc.contains("崇仁县相山镇") || qymc.contains("崇仁县航埠镇")
//                        || qymc.contains("崇仁县孙坊镇") || qymc.contains("崇仁县崇仁县河上镇")  || qymc.contains("崇仁县礼陂镇") || qymc.contains("崇仁县马鞍镇")
//                        || qymc.contains("崇仁县石庄乡") || qymc.contains("崇仁县六家桥乡") || qymc.contains("崇仁县白路乡")|| qymc.contains("崇仁县三山乡")
//                        || qymc.contains("崇仁县白陂乡") || qymc.contains("崇仁县桃源乡")   || qymc.contains("崇仁县许坊乡")|| qymc.contains("崇仁县郭圩乡")
                        || fzjgmc.contains("崇仁")
                        || zs.contains("崇仁县")
//                        || zs.contains("崇仁县巴山镇")  || zs.contains("崇仁县相山镇")  || zs.contains("崇仁县航埠镇")
//                        || zs.contains("崇仁县孙坊镇") || zs.contains("崇仁县崇仁县河上镇")  || zs.contains("崇仁县礼陂镇")  || zs.contains("崇仁县马鞍镇")
//                        || zs.contains("崇仁县石庄乡") || zs.contains("崇仁县六家桥乡")|| zs.contains("崇仁县白路乡")  || zs.contains("崇仁县三山乡")
//                        || zs.contains("崇仁县白陂乡") || zs.contains("崇仁县桃源乡")  || zs.contains("崇仁县许坊乡")  || zs.contains("崇仁县郭圩乡")
                        || scdz.contains("崇仁县")
//                        || scdz.contains("崇仁县巴山镇")   || scdz.contains("崇仁县相山镇")  || scdz.contains("崇仁县航埠镇")
//                        || scdz.contains("崇仁县孙坊镇") || scdz.contains("崇仁县河上镇")   || scdz.contains("崇仁县礼陂镇")  || scdz.contains("崇仁县马鞍镇")
//                        || scdz.contains("崇仁县石庄乡") || scdz.contains("崇仁县六家桥乡") || scdz.contains("崇仁县白路乡")   || scdz.contains("崇仁县三山乡")
//                        || scdz.contains("崇仁县白陂乡") || scdz.contains("崇仁县桃源乡")   || scdz.contains("崇仁县许坊乡")  || scdz.contains("崇仁县郭圩乡")
                        || scjydz.contains("崇仁县")
//                        || scjydz.contains("崇仁县巴山镇") || scjydz.contains("崇仁县相山镇")  || scjydz.contains("崇仁县航埠镇")
//                        || scjydz.contains("崇仁县孙坊镇") || scjydz.contains("崇仁县河上镇") || scjydz.contains("崇仁县礼陂镇")  || scjydz.contains("崇仁县马鞍镇")
//                        || scjydz.contains("崇仁县石庄乡") || scjydz.contains("崇仁县六家桥乡") || scjydz.contains("崇仁县白路乡")|| scjydz.contains("崇仁县三山乡")
//                        || scjydz.contains("崇仁县白陂乡") || scjydz.contains("崇仁县桃源乡") || scjydz.contains("崇仁县许坊乡")  || scjydz.contains("崇仁县郭圩乡")
                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省南昌市")){
                if (xzqhdm.contains("360100")
                        || xzqhdm.contains("360101") || xzqhdm.contains("360102") || xzqhdm.contains("360103")
                        || xzqhdm.contains("360104") || xzqhdm.contains("360105") || xzqhdm.contains("360111")
                        || xzqhdm.contains("360112") || xzqhdm.contains("360121") || xzqhdm.contains("360123")
                        || xzqhdm.contains("360124")
                        || qymc.contains("南昌")
//                        || qymc.contains("东湖区")  || qymc.contains("西湖区") || qymc.contains("青云谱区")
//                        || qymc.contains("湾里区") || qymc.contains("青山湖区")  || qymc.contains("新建区")
//                        || qymc.contains("南昌县") || qymc.contains("安义县") || qymc.contains("进贤县")
                        || fzjgmc.contains("南昌")
//                        || fzjgmc.contains("东湖区")|| fzjgmc.contains("西湖区") || fzjgmc.contains("青云谱区")|| fzjgmc.contains("湾里区")
//                        || fzjgmc.contains("青山湖区")|| fzjgmc.contains("新建区") || fzjgmc.contains("南昌县")|| fzjgmc.contains("安义县")
//                        || fzjgmc.contains("进贤县")
                        || zs.contains("南昌市")
                        || scdz.contains("南昌市")
                        || scjydz.contains("南昌市")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省景德镇市")){
                if (xzqhdm.contains("360200")
                        || xzqhdm.contains("360201") || xzqhdm.contains("360202") || xzqhdm.contains("360203")
                        || xzqhdm.contains("360222") || xzqhdm.contains("360281")
                        || qymc.contains("景德镇")
//                        || qymc.contains("昌江区")  || qymc.contains("珠山区") || qymc.contains("浮梁县")
//                        || qymc.contains("乐平市")
                        || fzjgmc.contains("景德镇")
                        || zs.contains("景德镇")
                        || scdz.contains("景德镇")
                        || scjydz.contains("景德镇")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省萍乡市")){
                if (xzqhdm.contains("360300")
                        || xzqhdm.contains("360301") || xzqhdm.contains("360302") || xzqhdm.contains("360313")
                        || xzqhdm.contains("360321") || xzqhdm.contains("360322") || xzqhdm.contains("360323")
                        || qymc.contains("萍乡")
//                        || qymc.contains("昌江区")  || qymc.contains("珠山区") || qymc.contains("浮梁县")
//                        || qymc.contains("乐平市")
                        || fzjgmc.contains("萍乡")
                        || zs.contains("萍乡")
                        || scdz.contains("萍乡")
                        || scjydz.contains("萍乡")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省九江市")){
                if (xzqhdm.contains("360400")
                        || xzqhdm.contains("360401") || xzqhdm.contains("360402") || xzqhdm.contains("360403")
                        || xzqhdm.contains("360404") || xzqhdm.contains("360423") || xzqhdm.contains("360424")
                        || xzqhdm.contains("360425") || xzqhdm.contains("360426") || xzqhdm.contains("360428")
                        || xzqhdm.contains("360429") || xzqhdm.contains("360430") || xzqhdm.contains("360481")
                        || xzqhdm.contains("360482") || xzqhdm.contains("360483")
                        || qymc.contains("萍乡市")
//                        || qymc.contains("濂溪区")  || qymc.contains("浔阳区") || qymc.contains("柴桑区")
//                        || qymc.contains("武宁县")  || qymc.contains("修水县") || qymc.contains("永修县")
//                        || qymc.contains("德安县")  || qymc.contains("都昌县") || qymc.contains("湖口县")
//                        || qymc.contains("彭泽县")  || qymc.contains("瑞昌市") || qymc.contains("共青城市") || qymc.contains("庐山市")
                        || fzjgmc.contains("萍乡")
                        || zs.contains("萍乡")
                        || scdz.contains("萍乡")
                        || scjydz.contains("萍乡")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省新余市")){
                if (xzqhdm.contains("360500")
                        || xzqhdm.contains("360501") || xzqhdm.contains("360502") || xzqhdm.contains("360521")
                        || qymc.contains("新余")
//                        || qymc.contains("渝水区")  || qymc.contains("分宜县")
                        || fzjgmc.contains("新余")
                        || zs.contains("新余")
                        || scdz.contains("新余")
                        || scjydz.contains("新余")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省鹰潭市")){
                if (xzqhdm.contains("360600")
                        || xzqhdm.contains("360601") || xzqhdm.contains("360602") || xzqhdm.contains("360603") || xzqhdm.contains("360681")
                        || qymc.contains("鹰潭")
//                        || qymc.contains("月湖区")  || qymc.contains("余江区")  || qymc.contains("贵溪市")
                        || fzjgmc.contains("鹰潭")
                        || zs.contains("鹰潭")
                        || scdz.contains("鹰潭")
                        || scjydz.contains("鹰潭")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省赣州市")){
                if (xzqhdm.contains("360700")
                        || xzqhdm.contains("360701") || xzqhdm.contains("360702") || xzqhdm.contains("360703") || xzqhdm.contains("360704")
                        || xzqhdm.contains("360722") || xzqhdm.contains("360723") || xzqhdm.contains("360724") || xzqhdm.contains("360725")
                        || xzqhdm.contains("360726") || xzqhdm.contains("360727") || xzqhdm.contains("360728") || xzqhdm.contains("360733")
                        || xzqhdm.contains("360734") || xzqhdm.contains("360735") || xzqhdm.contains("360781")
                        || qymc.contains("赣州")
                        || fzjgmc.contains("赣州")
                        || zs.contains("赣州")
                        || scdz.contains("赣州")
                        || scjydz.contains("赣州")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省吉安市")){
                if (xzqhdm.contains("360800")
                        || xzqhdm.contains("360801") || xzqhdm.contains("360802") || xzqhdm.contains("360803") || xzqhdm.contains("360821")
                        || xzqhdm.contains("360822") || xzqhdm.contains("360823") || xzqhdm.contains("360824") || xzqhdm.contains("360825")
                        || xzqhdm.contains("360826") || xzqhdm.contains("360827") || xzqhdm.contains("360828") || xzqhdm.contains("360829")
                        || xzqhdm.contains("360830") || xzqhdm.contains("360881")

                        || qymc.contains("吉安")
                        || fzjgmc.contains("吉安")
                        || zs.contains("吉安")
                        || scdz.contains("吉安")
                        || scjydz.contains("吉安")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省抚州市")){
                if (xzqhdm.contains("361000")
                        || xzqhdm.contains("361001") || xzqhdm.contains("361002") || xzqhdm.contains("361003") || xzqhdm.contains("361021")
                        || xzqhdm.contains("361022") || xzqhdm.contains("361023") || xzqhdm.contains("361024") || xzqhdm.contains("361025")
                        || xzqhdm.contains("361026") || xzqhdm.contains("361027") || xzqhdm.contains("361028") || xzqhdm.contains("361030")
                        || qymc.contains("抚州")
                        || fzjgmc.contains("抚州")
                        || zs.contains("抚州")
                        || scdz.contains("抚州")
                        || scjydz.contains("抚州")

                ) {
                    array.add(jsonobject);
                }
            }else if(environment.equals("江西省上饶市")){
                if (xzqhdm.contains("361100")
                        || xzqhdm.contains("361001") || xzqhdm.contains("361102") || xzqhdm.contains("361103") || xzqhdm.contains("361121")
                        || xzqhdm.contains("361123") || xzqhdm.contains("361124") || xzqhdm.contains("361125") || xzqhdm.contains("361126")
                        || xzqhdm.contains("361127") || xzqhdm.contains("361128") || xzqhdm.contains("361129") || xzqhdm.contains("361030") || xzqhdm.contains("361181")
                        || qymc.contains("上饶")
                        || fzjgmc.contains("上饶")
                        || zs.contains("上饶")
                        || scdz.contains("上饶")
                        || scjydz.contains("上饶")

                ) {
                    array.add(jsonobject);
                }
            }

        }
        return array;
    }
}
