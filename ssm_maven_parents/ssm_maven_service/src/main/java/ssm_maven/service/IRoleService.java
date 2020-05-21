package ssm_maven.service;

import ssm_maven.domain.Permission;
import ssm_maven.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(int id);

    List<Permission> findOtherPermission(int id);

    void addPermission(int roleId, int[] ids);
}
