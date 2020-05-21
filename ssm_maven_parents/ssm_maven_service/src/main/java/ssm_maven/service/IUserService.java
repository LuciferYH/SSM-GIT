package ssm_maven.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ssm_maven.domain.Role;
import ssm_maven.domain.UserInfo;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(int id);

    List<Role> findUserByIdAndAllRole(int id);

    void addRole(int userId, int[] roleIds);
}
