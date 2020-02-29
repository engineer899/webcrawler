package test.dao;

import org.apache.ibatis.annotations.Param;

public interface MatchPripidMapper {
    Integer matchPripidByUNISCID(String str);
    Integer matchPripidByENTNAME(String str);

}
