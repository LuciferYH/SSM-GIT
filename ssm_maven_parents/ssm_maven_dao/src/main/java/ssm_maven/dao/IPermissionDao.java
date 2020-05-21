package ssm_maven.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import ssm_maven.domain.Permission;

import java.util.List;

public interface IPermissionDao {

    //查询角色对应的权限
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findPermissionByRoleId(int roleId);


    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
