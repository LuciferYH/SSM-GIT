package ssm_maven.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm_maven.dao.IUserDao;
import ssm_maven.domain.Role;
import ssm_maven.domain.UserInfo;
import ssm_maven.service.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * spring-security.xml文件中指定了是哪个service
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    //加密类

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);

        //spring-security中的User,实现了UserDetails
        //将查询出来的用户对象封装成UserDetails
        //password那里必须加"{noop}"，否则无法识别
        //User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(), getAuthority(userInfo.getRoles()));

        //"{noop}"表示没有加密，是明文,不加"{noop}"表示会对登录的明文密码进行加密然后与数据库中的密文密码进行比较
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus()==1?true:false, true, true, true, getAuthority(userInfo.getRoles()));
        //status=0说明未开启，不可用
        //<security:intercept-url pattern="/**" access="ROLE_USER,ROLE_ADMIN"/>
        //只有ROLE_USER,ROLE_ADMIN才能访问，第三个参数就是这么个作用
        return user;
    }

    /**
     * 返回的是一个list集合，里面装的是角色描述
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            //spring-security.xml中的 <security:intercept-url pattern="/**" access="ROLE_USER,ROLE_ADMIN"/>对应
        }

        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findUserByIdAndAllRole(int id) {
        return userDao.findUserByIdAndAllRole(id);
    }

    @Override
    public void addRole(int userId, int[] roleIds) {
        for(int roleId :roleIds){
            //在users_role表中进行添加
            userDao.addRole(userId, roleId);
        }
    }
}
