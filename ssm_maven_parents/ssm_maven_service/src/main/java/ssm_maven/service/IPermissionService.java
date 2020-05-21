package ssm_maven.service;

import ssm_maven.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll();

    void save(Permission permission);
}
