package ssm_maven.dao;

import org.apache.ibatis.annotations.*;
import ssm_maven.domain.Role;
import ssm_maven.domain.UserInfo;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "ssm_maven.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();


    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "ssm_maven.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(int id);

    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findUserByIdAndAllRole(int id);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRole(@Param("userId") int userId, @Param("roleId") int roleId);
    //int userId, int roleId,多个参数，实际上最后填进去的是userId.userId,userId.roleId
}
