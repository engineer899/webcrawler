package test.util;

/**
 * @author 张伟
 * @date 2019/9/2 19:06
 */
public class ParsingData {
    private static int count =1 ;
//    private static String context="[";

    public static synchronized int getCount() {
        return count;
    }

    public static synchronized void setCount(int count) {
        ParsingData.count = count;
    }

//    public static synchronized String getContext() {
//        return context;
//    }
//
//    public static synchronized void setContext(String str) {
//        ParsingData.context = context+str;
//    }


    public static synchronized void addCount() {
        count++;
    }
}
