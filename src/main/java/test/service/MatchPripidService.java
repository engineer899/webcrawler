package test.service;

import org.apache.ibatis.session.SqlSession;
import test.dao.CheckTimeMapper;
import test.dao.MatchPripidMapper;
import test.util.D;

import java.io.IOException;

/**
 * @author 张伟
 * @date 2019/9/20 14:38
 */
public class MatchPripidService {
    private MatchPripidMapper matchPripidMapper;
    public MatchPripidService() throws IOException {
        SqlSession sqlSession= D.getConn();
        matchPripidMapper= sqlSession.getMapper(MatchPripidMapper.class);
    }

    public Integer matchPripidByUNISCID(String str){
        return matchPripidMapper.matchPripidByUNISCID(str);
    }
    public Integer matchPripidByENTNAME(String str){
        return matchPripidMapper.matchPripidByENTNAME(str);
    }
}
