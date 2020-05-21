package ssm_maven.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ssm_maven.dao.IRoleDao;
import ssm_maven.domain.Permission;
import ssm_maven.domain.Role;
import ssm_maven.service.IRoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermission(int id) {
        return roleDao.findOtherPermission(id);
    }

    @Override
    public void addPermission(int roleId, int[] ids) {
        for(int id:ids){
            roleDao.addPermission(roleId,id);
        }
    }
}
