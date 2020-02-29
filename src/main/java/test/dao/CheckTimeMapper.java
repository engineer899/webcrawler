package test.dao;

import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

public interface CheckTimeMapper {
    Timestamp checkUpTimeJX(@Param("TYPE") Integer TYPE);
    Timestamp checkUpTime(@Param("TYPE") Integer TYPE);
}
