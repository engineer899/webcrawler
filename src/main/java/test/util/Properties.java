package test.util;

import test.Main.Main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 张伟
 * @date 2019/9/19 10:04
 */
public class Properties {
    private static  java.util.Properties properties=null;

    static{
        properties = new java.util.Properties();
        InputStream in = Main.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(new InputStreamReader(in, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static String getEnvironment(){

        return properties.getProperty("environment");
    }

    public  static String getTablename(){

        return properties.getProperty("ZR_VE_BASEINFO");
    }

    public  static String getStartTime(){

        return properties.getProperty("StartTime");
    }
}
