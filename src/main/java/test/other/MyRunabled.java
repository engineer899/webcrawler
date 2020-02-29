package test.other;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import test.entity.Data;
import test.service.AddService;
import test.util.ParsingData;
import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import static test.Main.Main.*;

/**
 * @author 张伟
 * @date 2019/9/3 9:38
 */
public class MyRunabled implements Runnable {

    //每条许可证信息为一个json对象
    private  JsonElement jsonElement;
    //执行更新（添加）的service类
    private AddService addService;


    public void setJsonElement(JsonElement jsonElement) {
        this.jsonElement = jsonElement;
    }

    {
        try {
            addService = new AddService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



      //获取当前系统时间
    public static Date getNowDateShort() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateString =df.format(new java.util.Date());
        try {
            Date date= new Date(df.parse(dateString).getTime());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
      //将string类型数据转成java.sql.Date类型数据
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date= new Date(formatter.parse(strDate).getTime());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
      //将string类型数据转成Timestamp类型数据
    public static Timestamp strToTimestamp(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Timestamp date= new Timestamp(formatter.parse(strDate).getTime());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String,String>details=new HashMap<>();

    @Override
    public void run() {
        //初始化许可证信息的url数组
        Detailscurls.add(foodlicenceDetailsc);
        Detailscurls.add(foodlicenceDetailjy);
        Detailscurls.add(foodlicenceDetailxcy);
        Detailscurls.add(foodlicenceDetailbj);
        Detailscurls.add(medicinelicenceDetailjy);
        Detailscurls.add(instrumentDetailjy);
        Detailscurls.add(cosmeticDetail);

        Detailscurls.add(medicinelicenceDetailsc);        //药品生产许可证
        Detailscurls.add(instrumentDetailsc);             //医疗器械生产许可证

        System.out.println("正在更新《"+name[type-1]+ "》第"+ ParsingData.getCount() +"条数据");
        JsonObject jsonobject = jsonElement.getAsJsonObject();
        //将许可证表中对应字段的数据提取出来
        String LICENCE_TYPE = null;
        String LICENCE_NUM = null;
        String BUSINESS_ADDRESS=null;
        String ADMINISTRATION= null;
        String ADMIN_PERSON= null;
        String CONTENT= null;
        String UNISCID= null;
        String ADDRESS =null;
        String REGORG_ID=null;
        //不同类别的许可证信息的json数据中 对应的字段名可能不一样 这里需要分类处理
        if(type<6 || type==8){
            LICENCE_TYPE = jsonobject.get("ztyt").getAsString();  //主体业态
            LICENCE_NUM = jsonobject.get("xkzbh").getAsString();     //许可证编号
            BUSINESS_ADDRESS= jsonobject.get("scjydz").getAsString(); // 经营场所
            REGORG_ID = jsonobject.get("xzqhdm").getAsString();       //行政区划代码
            ADMINISTRATION= jsonobject.get("rcjgjg").getAsString();   // 监督管理机构
            ADMIN_PERSON= jsonobject.get("rcjgry").getAsString();     //  监督管理人员,多个用逗号隔开
            CONTENT=jsonobject.get("xkfwmc").getAsString();          // 许可内容
            UNISCID= jsonobject.get("shxydm").getAsString();          // 统一社会信用代码
            ADDRESS   = jsonobject.get("zs").getAsString();           // 地址
        }else if(type==6 || type==9){
            REGORG_ID = jsonobject.get("xzqhdm").getAsString();       //行政区划代码
            LICENCE_NUM = jsonobject.get("xkbh").getAsString();     //许可证编号
            BUSINESS_ADDRESS= jsonobject.get("scdz").getAsString(); // 经营场所
            ADDRESS   = jsonobject.get("zs").getAsString();           // 地址
        }else if(type==7){
            LICENCE_NUM = jsonobject.get("xkzbh").getAsString();     //许可证编号
            BUSINESS_ADDRESS= jsonobject.get("scjydz").getAsString(); // 经营场所
            UNISCID= jsonobject.get("shxydm").getAsString();          // 统一社会信用代码
        }
        String xkzid = jsonobject.get("xkzid").getAsString();        //ywztid与xkzid作为查询详情页面的参数
        String ywztid = jsonobject.get("ywztid").getAsString();
        String ENTNAME = jsonobject.get("qymc").getAsString();           //单位名称
        String START_TIME = jsonobject.get("fzrq").getAsString();        // 有效期自
        String END_TIME    = jsonobject.get("yxqz").getAsString();       // 有效期至
        String REGORG = jsonobject.get("fzjgmc").getAsString();          //发证机关
        String STATUS = jsonobject.get("xkzzt").getAsString();           //状态（0无效 1有效
        String SIGNER  = jsonobject.get("qfr").getAsString();            // 签发人
        String PUBLIC_DATE  = jsonobject.get("fzrq").getAsString();      // 公示日期
        String ADD_TIME = jsonobject.get("rksj").getAsString();          // 入库时间



       if(STATUS.equals("有效")){ STATUS="1"; }else{ STATUS="0"; }
       //按规范整理好数据 方便入库
       if(type==1){
            LICENCE_TYPE="1";//食品生产许可证
       }else if(type==2){
           if(LICENCE_TYPE.equals("15")){
               LICENCE_TYPE="11";//单位食堂
           }else if(LICENCE_TYPE.equals("13")){
               LICENCE_TYPE="2";//食品销售许可证
           }else if(LICENCE_TYPE.equals("14")){
               LICENCE_TYPE="10";//餐饮服务许可证
           }
       }else if(type==3){ LICENCE_TYPE="3";//小餐饮小食杂经营登记证
       }else if(type==4){ LICENCE_TYPE="9";//保健食品生产许可证
       }else if(type==5){ LICENCE_TYPE="5";//药品经营许可证
       }else if(type==6){
           LICENCE_TYPE="7";//医疗器械经营许可证
           details= DetailsYlqx(Detailscurls.get(type-1), xkzid, ywztid);  //进入详情页获取完整信息
           if(details.get("CONTENT")!=null){ CONTENT=details.get("CONTENT").toString(); }
       }else if(type==7){
           LICENCE_TYPE="8";//化妆品生产许可证
           details= DetailsHzp(Detailscurls.get(type-1), xkzid, ywztid);  //进入详情页获取完整信息
           if(details.get("CONTENT")!=null){ CONTENT=details.get("CONTENT").toString(); }
           if(details.get("ADDRESS")!=null){ ADDRESS=details.get("ADDRESS").toString(); }
           if(details.get("ADMINISTRATION")!=null){ ADMINISTRATION=details.get("ADMINISTRATION").toString(); }
           if(details.get("ADMIN_PERSON")!=null){ ADMIN_PERSON=details.get("ADMIN_PERSON").toString(); }
       }else if(type==8){
           LICENCE_TYPE="4";//药品生产许可证
       }else if(type==9){
           LICENCE_TYPE="6";//医疗器械生产许可证
           details= DetailsYlqx(Detailscurls.get(type-1), xkzid, ywztid);  //进入详情页获取完整信息
           if(details.get("CONTENT")!=null){ CONTENT=details.get("CONTENT").toString(); }
       }


        //处理不规则字符 将监督管理人员,多个用逗号隔开
        if(ADMIN_PERSON!=null){
            ADMIN_PERSON=ADMIN_PERSON
                    .replace("1", "")
                    .replace("2", "")
                    .replace("3", "")
                    .replace("4", "")
                    .replace("5", "")
                    .replace("6", "")
                    .replace("7", "")
                    .replace("8", "")
                    .replace("9", "")
                    .replace(";", ",")
                    .replace("；", ",")
                    .replace(":", ",")
                    .replace("：", ",")
                    .replace(" ", ",")
                    .replace("，", ",")
                    .replace("、", ",")
                    .replace(",,,,,", ",")
                    .replace(",,,,", ",")
                    .replace(",,,", ",")
                    .replace(",,", ",");
        }else{
            ADMIN_PERSON="";
        }
        if(ADMINISTRATION==null){
            ADMINISTRATION="";
        }
        if(UNISCID==null){
            UNISCID="";
        }
        if(REGORG_ID==null){
            REGORG_ID="";
        }

        Date startTime= strToDateLong(START_TIME);
        Date endTime= strToDateLong(END_TIME);

        //获取当前时间
       // Date addTime = getNowDateShort();

        java.sql.Timestamp addTime = strToTimestamp(ADD_TIME);
        Data data=new Data(
                        ENTNAME,
                        LICENCE_TYPE,
                        startTime,
                        endTime,
                        REGORG,
                        REGORG_ID,
                        STATUS,
                        addTime,
                        "admin",
                        LICENCE_NUM,
                        SIGNER,
                        endTime,
                        ADDRESS,
                        BUSINESS_ADDRESS,
                        ADMINISTRATION,
                        ADMIN_PERSON,
                        UNISCID,
                        CONTENT);
           try{
               //将处理好的数据入库
               if (environment.equals("江西省")) {//如果项目要求为爬取整个省的数据 则录入整个省的许可证数据
                       addService.addJX(data);
               } else{
                       addService.addLicence(data);
               }
           }catch (Exception e){
               //将入库异常的数据 保存到文件中 方便后期补录
               e.printStackTrace();
               try{
                   Gson gson=new Gson();
                   //true = append file gson.toJson(data).toString()
                   FileWriter fileWritter = new FileWriter("D:\\exception.txt",true);
                   fileWritter.write(gson.toJson(data).toString()+"\r\n" );
                   fileWritter.close();
                   System.out.println("Done");
               }catch(IOException io){
                   io.printStackTrace();
               }
           }
           ParsingData.addCount();

//         System.out.println("企业名称"+ENTNAME);
//         System.out.println("发证日期"+START_TIME);
//         System.out.println("有效期至"+END_TIME);
//         System.out.println("反证机关"+REGORG);
//         String result = Datatojson(Detailurl, xkzid, ywztid);
//         if(result!=null){
//            ParsingData.addCount();
//         }
        // Thread.sleep(1000);
//         String string="{"+"\"xkzid\""+":"+"\""+xkzid+"\""+","+ "\"ywztid\""+":"+"\""+ywztid+"\""+","
//                +"\"ENTNAME\""+":"+"\""+ENTNAME+"\""+","
//                +"\"START_TIME\""+":"+"\""+START_TIME+"\"" +","
//                +"\"END_TIME\""+":"+"\""+END_TIME+"\""+","
//                +"\"REGORG\""+":"+"\""+REGORG+"\"" +","
//                +"\"REGORG_ID\""+":"+"\""+REGORG_ID+"\""+","
//                +"\"LICENCE_NUM\""+":"+"\""+LICENCE_NUM+"\""+","
//                +"\"ADDRESS\""+":"+"\""+ADDRESS+"\"" +","
//                +"\"BUSINESS_ADDRESS\""+":"+"\""+BUSINESS_ADDRESS+"\""+","
//                + result + "},";
//         ParsingData.setContext(string);
    }


}
