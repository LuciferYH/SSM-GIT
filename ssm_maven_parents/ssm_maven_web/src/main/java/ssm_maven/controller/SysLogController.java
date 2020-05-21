package ssm_maven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm_maven.domain.SysLog;
import ssm_maven.service.ISysLogService;

import java.util.List;

@RequestMapping("/sysLog")
@Controller
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll();
        mv.addObject("sysLogs", sysLogList);
        mv.setViewName("syslog-list");
        return mv;
    }
}
