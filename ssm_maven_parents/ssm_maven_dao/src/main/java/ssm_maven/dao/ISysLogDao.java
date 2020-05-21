package ssm_maven.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ssm_maven.domain.SysLog;

import java.util.List;

public interface ISysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) " +
            "values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);


    @Select("select * from syslog")
    List<SysLog> findAll();
}
