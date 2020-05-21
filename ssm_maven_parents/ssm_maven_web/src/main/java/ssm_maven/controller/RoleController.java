package ssm_maven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm_maven.domain.Permission;
import ssm_maven.domain.Role;
import ssm_maven.service.IRoleService;

import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(int id){
        ModelAndView mv = new ModelAndView();
        //根据id查找role
        Role role = roleService.findById(id);
        mv.addObject("role", role);
        //查找该角色没有的权限
        List<Permission> permissionList = roleService.findOtherPermission(id);
        mv.addObject("permissionList", permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) int roleId, @RequestParam(name = "ids") int[] ids){
        roleService.addPermission(roleId,ids);
        return "redirect:findAll";
    }
}
