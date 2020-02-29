package test.service;

import org.apache.ibatis.session.SqlSession;
import test.dao.CheckTimeMapper;
import test.util.D;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author 张伟
 * @date 2019/9/6 14:39
 */
public class CheckTimeService {
    private CheckTimeMapper checkTimeMapper;
    public CheckTimeService() throws IOException {
        SqlSession sqlSession= D.getConn();
        checkTimeMapper= sqlSession.getMapper(CheckTimeMapper.class);
    }
    public Timestamp checkUpTimeJX(Integer TYPE){ return checkTimeMapper.checkUpTimeJX(TYPE); }
    public Timestamp checkUpTime(Integer TYPE){ return checkTimeMapper.checkUpTime(TYPE); }}
