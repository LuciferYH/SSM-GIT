package ssm_maven.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ssm_maven.domain.Orders;
import ssm_maven.service.IOrdersService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList", ordersList);
        mv.setViewName("orders-list");
        return mv;
    }

    @Secured("ROLE_ADMIN")//只有ROLE_ADMIN角色可以访问
    @RequestMapping("/findByPages")
    public ModelAndView findByPages(@RequestParam(name = "pageNum",required = true,defaultValue = "1")Integer pageNum,
        @RequestParam(name = "pageSize",required = true,defaultValue = "10")Integer pageSize){
        //所有参数最好都用包装类，可以避免很多不必要的麻烦
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findByPages(pageNum, pageSize);

        //一个分页bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true)int ordersId){
        ModelAndView mv = new ModelAndView();

        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
