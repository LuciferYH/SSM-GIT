package ssm_maven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm_maven.dao.IPermissionDao;
import ssm_maven.domain.Permission;
import ssm_maven.service.IPermissionService;

import java.util.List;

@RequestMapping("/permission")
@Controller
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }
}
