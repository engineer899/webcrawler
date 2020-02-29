package test.Main;

import com.google.gson.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import test.other.IPBean;
import test.other.IPList;
import test.other.IPSpider;
import test.other.MyRunabled;
import test.service.CheckTimeService;
import test.service.MatchPripidService;
import test.util.*;
import test.util.Properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;




/**
 * @author 张伟
 * @date 2019年9月5日
 */
public class Main {

    public static int thisip;
    public static int flag;
    public static  List<IPBean> iplist=new ArrayList<>();
    public static Integer type; //当前爬取许可证的类型
    public static boolean updata;//是否开始更新标识
    public static String  environment;//该值由配置文件指定 用来标识要爬取哪个地区的许可证数据
    public static String  tablename;//该值由配置文件指定 用来标识当匹配pripid时 使用的企业信息表的名称
    public static Integer end; //设置要爬取几类许可证的信息



    public static synchronized List<IPBean> getiplist(){
        return iplist;
    }
    public static synchronized int getthisip(){
        return thisip;
    }
    public static synchronized int getflag(){
        return flag;
    }
    public static synchronized void addflag(){
        flag++;
    }
    public static synchronized void addthisip(){
        thisip++;
    }
    public static synchronized void setIplist(List<IPBean> ip){
        iplist=ip;
    }
    public static synchronized void setThisip(int a){
        thisip=a;
    }
    public static synchronized void setFlag(int b){ flag=b; }

    //食品生产许可证
    public static String foodlicencelistsc="http://sjzy.jxfda.gov.cn:7089/sjfb/foodlicence/queryList.xhtml?xkzlx=111&random=0.4591553522887777&qymc=&xkzbh=&qyfzr=&fddbr=";
    public static String foodlicenceDetailsc="http://sjzy.jxfda.gov.cn:7089/sjfb/foodlicence/gotoFoodProDetail.xhtml?";
    //食品经营许可证
    public static String foodlicencelistjy="http://sjzy.jxfda.gov.cn:7089/sjfb/foodlicence/queryList.xhtml?xkzlx=121&random=0.2896148337534322&qymc=&xkzbh=&qyfzr=&fddbr=";
    public static String foodlicenceDetailjy="http://sjzy.jxfda.gov.cn:7089/sjfb/foodlicence/gotoFoodManagementDetail.xhtml?";
    //小餐饮小食杂经营登记证
    public static String foodlicencelistxcy="http://sjzy.jxfda.gov.cn:7089/sjfb/foodlicence/queryList.xhtml?xkzlx=122&random=0.7190109031234436&qymc=&xkzbh=&qyfzr=&scjydz=";
    public static String foodlicenceDetailxcy="http://sjzy.jxfda.gov.cn:7089/sjfb/foodlicence/gotoXcyDetail.xhtml?";
    //保健食品生产许可证
    public static String foodlicencelistbj="http://sjzy.jxfda.gov.cn:7089/sjfb/foodlicence/queryList.xhtml?xkzlx=131&random=0.22847555945557863&qymc=&xkzbh=&fddbr=&scjydz=";
    public static String foodlicenceDetailbj="http://sjzy.jxfda.gov.cn:7089/sjfb/foodlicence/gotoBjspDetail.xhtml?";
    //药品经营许可证
    public static String medicinelicencelistjy="http://sjzy.jxfda.gov.cn:7089/sjfb/medicinelicence/queryList.xhtml?xkzlx=221&random=0.8339014960230231&qymc=&xkzbh=&zsbh=&jyfs=&zcdz=";
    public static String medicinelicenceDetailjy="http://sjzy.jxfda.gov.cn:7089/sjfb/medicinelicence/gotoManageCompanyDetail.xhtml?";
    //医疗器械经营
    public static String instrumentlistjy="http://sjzy.jxfda.gov.cn:7089/sjfb/instrument/queryList.xhtml?xkzlx=321&random=0.5313242245050365&xkbh=&qymc=&zcdz=&fddbr=";
    public static String instrumentDetailjy="http://sjzy.jxfda.gov.cn:7089/sjfb/instrument/gotoInstrumentJyDetail.xhtml?";
    //化妆品
    public static String cosmeticlist="http://sjzy.jxfda.gov.cn:7089/sjfb/cosmetic/queryList.xhtml?xkzlx=411&random=0.026225057373247318&xkzbh=&qymc=&shxydm=&fddbr=";
        public static String cosmeticDetail="http://sjzy.jxfda.gov.cn:7089/sjfb/cosmetic/gotoCosmeticDetail.xhtml?";
    //药品生产许可证
    public static String medicinelicencelistsc="http://sjzy.jxfda.gov.cn:7089/sjfb/medicinelicence/queryList.xhtml?xkzlx=211&random=0.07472477149984103&qymc=&xkzbh=&shxydm=&fddbr=";
    public static String medicinelicenceDetailsc="http://sjzy.jxfda.gov.cn:7089/sjfb/medicinelicence/gotoProCompanyDetail.xhtml?";
    //医疗器械生产许可证
    public static String instrumentlistsc="http://sjzy.jxfda.gov.cn:7089/sjfb/instrument/queryList.xhtml?xkzlx=311&random=0.4983035194173425&xkbh=&qymc=&zs=&fddbr=";
    public static String instrumentDetailsc="http://sjzy.jxfda.gov.cn:7089/sjfb/instrument/gotoInstrumentScDetail.xhtml?";


    public static String name[]={"食品生产许可证","食品经营许可证","小餐饮小食杂经营登记证","保健食品生产许可证","药品经营许可证","医疗器械经营","化妆品","药品生产许可证","医疗器械生产许可证"};

    //用来爬取许可证信息的url数组
    public static ArrayList<String> urls=new ArrayList<>();
    //用来爬取许可证详情信息的url数组
    public static ArrayList<String> Detailscurls=new ArrayList<>();



    public static void main(String[] args) throws ParseException {
        //定时执行更新任务
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                try {
                    work();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer=new Timer();
        long intevalPeriod=24*60*60*1000; //设置更新时间间隔
        long nowtime=System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString =formatter.format(nowtime);
        String startTime=Properties.getStartTime();
        dateString=dateString+" "+startTime; //设置每天开始更新的时间
        System.out.println("程序开始运行时间："+dateString);
        Date date= new Date(formatter2.parse(dateString).getTime());
        timer.scheduleAtFixedRate(task,date,intevalPeriod);
//        timer.scheduleAtFixedRate(task,date,intevalPeriod);
    }


    //自动检查更新函数
    public static void work() throws IOException {
        urls.clear();
        Detailscurls.clear();
        ParsingData.setCount(0);
        flag=0;
        urls.add(foodlicencelistsc);        //添加食品生产许可证url
        urls.add(foodlicencelistjy);        //添加食品经营许可证url
        urls.add(foodlicencelistxcy);       //添加小餐饮小食杂经营登记证url
        urls.add(foodlicencelistbj);        //添加保健食品生产许可证url
        urls.add(medicinelicencelistjy);    //添加药品经营许可证url
        urls.add(instrumentlistjy);         //添加医疗器械经营url
        urls.add(cosmeticlist);             //添加化妆品url
        //在崇仁市场监管项目中只需要爬取以上七种许可证信息

        urls.add(medicinelicencelistsc);    //药品生产许可证
        urls.add(instrumentlistsc);         //医疗器械生产许可证

        //从配置文件中读取当前所需要爬取哪个项目的数据
        environment= Properties.getEnvironment();
        tablename=Properties.getTablename();
        if(environment.equals("江西省抚州市崇仁县")){
            end=7;//崇仁县市场监管项目只需要爬取七种许可证信息
        }else{
            end=9;//其他项目默认爬取全部许可证信息
        }

        //遍历每个url 爬取各类许可证信息
        for(type=1;type<=end;type++){
            updata=false;
            ParsingData.setCount(1);
            String listurl=urls.get(type-1);//获取可返回许可证信息列表的url
            System.out.println("正在爬取《"+name[type-1]+"》数据................................");
            Integer pagez=1; //当许可证信息列表太大时 根据数据总数分多次爬取 默认1次爬完
            Integer rows=10; //默认每次爬10条
            //第一次访问 获取数据总数
            String testjson = sendGet(listurl,"1", "10");
            JsonParser parser = new JsonParser();
            JsonObject test=parser.parse(testjson).getAsJsonObject();
            //获取数据总数
            Integer total=test.get("total").getAsInt();
            if(total<=10000){
                //如果数据总数小于一万 则一次爬完
                rows=total;
                System.out.println(total+" 条数据一次爬完");
            }else if(total>10000 && total<1000000){
                //如果数据总数在一万到一百万之间 则每次爬一万条
                rows=10000;
                pagez=(total/10000)+1;
                System.out.println(total+" 条数据分"+pagez+"页爬完");
            }else{
                //如果数据总数超过一百万 则每次爬10万条
                rows=100000;
                pagez=(total/10000)+1;
                System.out.println(total+" 条数据分"+pagez+"页爬完");
            }

            CheckTimeService checkTimeService=new CheckTimeService();
            java.sql.Timestamp lastaddtime=null;
            if (environment.equals("江西省")) {//当要爬取整个省许可证信息时
                lastaddtime= checkTimeService.checkUpTimeJX(type);//查询数据库中当前许可证信息最后一次更新时间
            }else{
                lastaddtime= checkTimeService.checkUpTime(type);//查询数据库中当前许可证信息最后一次更新时间
            }
            //许可证信息分多次爬完
            for(Integer i=1;i<=pagez;i++) {
                System.out.println("正在爬取第"+i+"页数据");
                //获取许可证信息列表json字符串  //设置当前页码    //设置每次爬多少条数据
//                String str =sendGet(listurl,"1", "50");
                String str =sendGet(listurl,i.toString(), rows.toString());
//                System.out.println(str);
                try {
                    str = str.substring(str.indexOf('['), str.length() - 1);
//                    System.out.println(str);
//                    str=str.replaceAll("\\\\", ";");
                    System.out.println("成功取得许可证信息json字符串！");
                    //将json字符串转为json数组
                    JsonArray array=null;
                    try{
                         array = parser.parse(str).getAsJsonArray();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    //筛选出指定地区的数据
                    array = SiftedData.selectdata(array,environment);
                    System.out.println("筛选出数据"+array.size()+"条");
                    if(lastaddtime!=null){
                        //根据数据库中最后一条被更新数据的添加时间与所爬取数据的“入库时间”比较 筛选出需要更新的数据
                        array=selectupdata(array,lastaddtime);
                    }else if(i==pagez){
                        //如果lastaddtime为空 既数据库中该类许可证数据还未添加 需要全部添加
                        updata=true; //当前类别许可证信息全部爬取下来后才开始执行更新
                    }
                    //将筛选出的数据进行整理,补充再入库
                    getparameter(array);
                    //当前类别需要更新或添加的许可证信息已经全部爬取并更新完成 下面将更新后的数据匹配上pripid 再退出循环 进入下一类别许可证更新任务
                    if(updata){
                        MatchPripidService matchPripidService=new MatchPripidService();
                        //根据许可证信息中的企业名称来匹配pripid    tablename由配置文件中指定 因为不同项目中的企业信息表不一样 所以匹配pripid时用的企业信息表根据配置文件来
                        matchPripidService.matchPripidByUNISCID(tablename);
                        D.closeConn();//提交事务
                        //根据许可证信息中的统一社会信用代码来匹配pripid
                        matchPripidService=new MatchPripidService();
                        matchPripidService.matchPripidByENTNAME(tablename);
                        D.closeConn();
                        System.out.println("《"+name[type-1]+"》数据更新完成");
                        System.out.println(" ");
                        break;//退出当前循环
                    }
//                  int length=ParsingData.getContext().length()-1;
//                  ParsingData.setContext(ParsingData.getContext().substring(0,length)+"]");
//                  System.out.println(ParsingData.getContext());
                } catch (Exception e) {
                    System.out.println("出现异常" + ParsingData.getCount());
                    e.printStackTrace();
                }
            }
        }
    }

    //筛选出需要更新的数据
    public static JsonArray selectupdata(JsonArray array, java.sql.Timestamp lastaddtime) {
        System.out.println("");
        System.out.println("检查更新开始.......");
        JsonArray array1=new JsonArray();
        java.sql.Timestamp lastuptime= null;
        for (JsonElement jsonElement : array) {
            JsonObject jsonobject = jsonElement.getAsJsonObject();
            String dateString=jsonobject.get("rksj").getAsString();
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                lastuptime= new java.sql.Timestamp(df.parse(dateString).getTime());//获取网站最近数据更新时间
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(lastaddtime==null){         //当数据库还没录入此类数据时
                updata=false; //第一次入库 不用筛选
                System.out.println("该类数据第一次入库 ");
                return array;
            }else if(lastuptime.after(lastaddtime) && lastaddtime!=null) {            //当网站更新时间大于数据入库数据则需要更新
                array1.add(jsonobject);
            }else{
                updata=true; //全部更新完成
                break;
            }
        }
        System.out.println("当前页检查更新完成！ "+array1.size()+"条需要更新");
        return array1;
    }

    //发送获取企业信息列表的url 并将信息以json字符串返回
    public static String sendGet(String url, String a,String b) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "&page="+a+"&rows="+b;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    //遍历每个json对象 将查询企业详情接口需要的两个参数取出 并查询详情
    //为了方便改成多线程爬取 将主要方法写在Runable类中 不需要用多线程时只调用run方法
    public static void getparameter(JsonArray array) {
        // int i=0;
        //创建一个线程池
        //ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        System.out.println("");
        System.out.println("开始更新......");
        for (JsonElement jsonElement : array) {
            MyRunabled runnable=new MyRunabled();
            runnable.setJsonElement(jsonElement);
            runnable.run();   //不需要用多线程时只调用run方法
           // fixedThreadPool.execute(runnable);
        }
//        while (true){
//            // 判断所有副线程是否完成
//            if (ParsingData.getCount() == array.size()){
//                System.out.println("所有副线程结束");
//                fixedThreadPool.shutdown();
//                break;
//            }
//            System.out.println("所有副线程未结束");
//
//        }

    }



    //医疗器械经营(生产)企业信息详情
    public static synchronized Map<String,String> DetailsYlqx(String url, String xkzid,String ywztid) {
        Map<String,String> map = new HashMap<String,String>();
        url=url+"xkzid="+xkzid+"&ywztid="+ywztid;
        Document doc= connect(url);
        Elements table = doc.getElementsByClass("table-info");
        for (Element tag : table) {
            Elements tags =tag.getElementsByTag("td");
            map.put("CONTENT",tags.get(6).text());
        }
        return map;
    }

    //化妆品生产许可证详情
    public static synchronized Map<String,String> DetailsHzp(String url, String xkzid,String ywztid) {
        Map<String,String> map = new HashMap<String,String>();
        url=url+"xkzid="+xkzid+"&ywztid="+ywztid;
        Document doc= connect(url);
        Elements table = doc.getElementsByClass("table-info");
        for (Element tag : table) {
            Elements tags =tag.getElementsByTag("td");
            map.put("CONTENT",tags.get(2).text());
            map.put("ADDRESS",tags.get(3).text());
            map.put("ADMINISTRATION",tags.get(11).text());
            map.put("ADMIN_PERSON",tags.get(12).text());
        }
        return map;
    }

    //获取详情页面Document
    public static Document connect(String url) {
        Random random = new Random();
        Document document=null;
        String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
                "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};
        // logger.info("检测中------ {}:{}",ipmap.get("ip"),ipmap.get("port") );
        System.out.println("connect:" + url);
        long start = System.currentTimeMillis();//开始时间
        /*try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("The input url('" + url + "') is invalid!");
        }
        while(1==1){
            try {
                for (;getthisip()<getiplist().size();addthisip()){
                    int i=random.nextInt(14);
//                    long endconntent;
                    try {
                        if(getiplist().size()!=0)
                        System.out.println("正在使用list中第"+getthisip()+"个ip: "+getiplist().get(getthisip()).getIp()+":"+getiplist().get(getthisip()).getPort());
//                        long startconntent=System.currentTimeMillis();
                        document = Jsoup.connect(url)
                                .timeout(4000)
                                .proxy(getiplist().get(getthisip()).getIp(),getiplist().get(getthisip()).getPort())//用代理ip访问
                                .ignoreContentType(true)
                                .userAgent(ua[i])
                                .get();
//                        endconntent=System.currentTimeMillis()-startconntent;
                    } catch (IOException e1) {
                        System.out.println("当前ip使用 "+getflag()+" 次后失效，换ip");
                        setFlag(0);
                        continue;
                    }
                    if(document!=null){
                        if (getflag()==9){
                            setFlag(0);
                            addthisip();
                        }
                        addflag();
                        break;
                    }
                }
                if(document==null){
                    System.out.println("循环完ipList也没找到获取到详情页面 换一批ip");
                    setIplist(Getip());
                    setThisip(0);
                    Thread.sleep(3000);
                    System.out.println("开始下一次循环！！！！");
                    continue;
                }else{
                    break;
                }
            } catch (Exception e) {
            } finally {
                System.out.println("成功获取详情页面 用时:" + (System.currentTimeMillis() - start) + "ms");//结束时间
            }
        }
        return document;
    }

    //获取代理ip列表
    public static List<IPBean> Getip(){
        System.out.println("Getip Start: ");
        IPSpider spider = new IPSpider();
        List<IPBean> list = spider.crawlHttp(5);
        System.out.println("爬取ip数量：" + list.size());
        Gson gson = new Gson();
        //使用多线程快速筛选有用筛选ip
        IPList.getiplist().clear();
        IPList.setCount(0);
        for (IPBean ipBean : list) {
            System.out.println(gson.toJson(ipBean));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean valid = IPUtils.isValid(ipBean);
                    if (valid){
                        IPList.add(ipBean);
                    }
                    IPList.increase();
                }
            }).start();
        }

        while (true){
            // 判断所有副线程是否完成
            if (IPList.getCount() == list.size()){
                System.out.println("有效ip数量：" + IPList.getSize());
                break;
            }
        }
        //返回有效ip数组
        return IPList.getiplist();
    }










    //    public static  void method(){
//        for (;getthisip()<getiplist().size();){
//            int i=random.nextInt(14);
//            long endconntent;
//            try {
//                if(getiplist().size()!=0)
//                    System.out.println("list中第"+getthisip()+"个ip: "+getiplist().get(getthisip()).getIp()+":"+getiplist().get(getthisip()).getPort());
//                long startconntent=System.currentTimeMillis();
//                document = Jsoup.connect(url)
//                        .timeout(4000)
//                        .proxy(getiplist().get(getthisip()).getIp(),getiplist().get(getthisip()).getPort())//用代理ip访问
//                        .ignoreContentType(true)
//                        .userAgent(ua[i])
//                        .get();
//                endconntent=System.currentTimeMillis()-startconntent;
//            } catch (IOException e1) {
//                System.out.println("错误！！"+getflag());
//                int flag=getflag();
//                int size=getiplist().size()-2;
//                int thisip=getthisip();
//                if (flag<100 && thisip>size){
//                    setThisip(0);
//                    setFlag(0);
//                }else {
//                    addthisip();
//                    addflag();
//                }
//                continue;
//            }
//            if(document!=null){
//                System.out.println("找到页面 用时："+endconntent+"ms"+getflag());
//                int flag=getflag();
//                int size=getiplist().size()-2;
//                int thisip=getthisip();
//                if (flag<100 && thisip>size){
//                    setThisip(0);
//                    setFlag(0);
//                }else {
//                    addthisip();
//                    addflag();
//                }
//                break;
//            }
//        }
//    }
}
