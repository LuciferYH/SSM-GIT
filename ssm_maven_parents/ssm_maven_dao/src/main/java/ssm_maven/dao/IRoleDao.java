package ssm_maven.dao;

import org.apache.ibatis.annotations.*;
import ssm_maven.domain.Permission;
import ssm_maven.domain.Role;

import java.util.List;

public interface IRoleDao {

    //Role中有属性List<Permission> permissions
    @Select("select * from role Where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "ssm_maven.dao.IPermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(int userId);


    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id = #{id}")
    Role findById(int id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findOtherPermission(int id);

    @Insert("insert into role_permission values(#{roleId},#{id})")
    void addPermission(@Param("roleId") int roleId, @Param("id") int id);
}
