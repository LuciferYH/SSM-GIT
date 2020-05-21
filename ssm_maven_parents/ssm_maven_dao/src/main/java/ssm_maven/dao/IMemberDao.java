package ssm_maven.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm_maven.domain.Member;

@Repository
public interface IMemberDao {

    @Select("select * from member where id = #{id}")
    Member findById(int id);
}
