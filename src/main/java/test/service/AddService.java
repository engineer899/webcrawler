package test.service;

import org.apache.ibatis.session.SqlSession;
import test.dao.AddMapper;
import test.entity.Data;
import test.util.D;

import java.io.IOException;
import java.sql.Date;

/**
 * @author 张伟
 * @date 2019/8/7 22:55
 */
public class AddService {
    private AddMapper addMapper;
    public AddService() throws IOException {
        SqlSession sqlSession= D.getConn();
        addMapper= sqlSession.getMapper(AddMapper.class);
    }
    public Integer addJX(Data data){return addMapper.addJX(data);  }
    public Integer addLicence(Data data){return addMapper.addLicence(data); }
}
