package ssm_maven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm_maven.domain.Role;
import ssm_maven.domain.UserInfo;
import ssm_maven.service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //只有用户名为赵四的可以访问
    @PreAuthorize("authentication.principal.username=='赵四'")
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)int id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 通过用户id查找该用户没有的角色
     * @return
     */
    @RequestMapping("findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(int id){
        ModelAndView mv = new ModelAndView();
        //在添加角色时，需要用户id，所以将用户传进去
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        List<Role> roleList = userService.findUserByIdAndAllRole(id);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     * @param userId 、<input type="hidden" name="userId" value="${user.id}">
     * @param roleIds   <input name="ids" type="checkbox" value="${role.id}">
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) int userId,@RequestParam(name = "ids",required = true)int[] roleIds){
        userService.addRole(userId,roleIds);
        return "redirect:findAll";
    }
}
