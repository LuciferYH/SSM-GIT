package ssm_maven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm_maven.domain.Product;
import ssm_maven.service.IProductService;

import javax.annotation.security.RolesAllowed;
import java.util.List;


/**
 * maven父子工程启动有三种方法
 * 1.maven的tomcat插件,直接在父工程的maven中tomcat7:run
 * 2.maven的tomcat插件，父工程将代码打包之后（install）,web子工程再运行
 *    maven项目启动之前，都会加载jar包，先是本地仓库，再到远程仓库查找，
 *    如果父工程不进行install，就找不到相应的jar包
 *    因此，每次大的代码修改时，对于父工程先clean，再install，进行一个jar包的更新
 * 3.本地tomcat,将wb模块加载进本地tomcat
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/saveProduct")
    public String saveProduct(Product product){
        System.out.println("web:保存方法执行了");
        productService.save(product);

        return "redirect:findAll";
        // 重定向进入url：findAll
    }

    @RequestMapping("/findAll")
    @Secured("ROLE_ADMIN")//只有ROLE_ADMIN角色可以访问
    public ModelAndView findAll(){
        System.out.println("web层：查询所有方法执行");
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAllProduct();
        //也会将products存入request域中
        mv.addObject("productList", products);
        //mv.setViewName("list");
        mv.setViewName("product-list1");
        return mv;
    }
}
