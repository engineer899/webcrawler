package test.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 张伟
 * @date 2019/8/7 22:52
 */
public class D {
    private static SqlSessionFactory ssf;
    private static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<SqlSession>();
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            ssf = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            System.out.println("配置文件读取错误！");
        }
    }
    public static SqlSession getConn() throws IOException{
        SqlSession sqlSession=threadLocal.get();
        if(sqlSession==null){
            sqlSession=ssf.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }
    public D(){
    }
    public static void closeConn(){
        SqlSession sqlSession=threadLocal.get();
        if(sqlSession!=null){
            sqlSession.commit();
            sqlSession.close();
            threadLocal.remove();
            System.out.println("提交事务！");
        }
    }
}
