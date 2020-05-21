package ssm_maven.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm_maven.domain.SysLog;
import ssm_maven.service.ISysLogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component//除了@Controller,@Service,@Repository之外，其他类所用的注解，相当于在xml文件中配置了一个Bean
@Aspect//切面类
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date startTime;//访问时间
    private Class visitClass;//访问的类
    private Method method;//访问的方法

    //前置通知，主要获取访问时间，访问的类，访问的方法
    @Before("execution(* ssm_maven.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime = new Date();//访问时间

        //获取访问的类
        visitClass = jp.getTarget().getClass();
        //获取访问的方法的方法名
        String methodName = jp.getSignature().getName();

        //获取访问方法的参数
        Object[] args = jp.getArgs();
        //无参数
        if(args==null || args.length==0){
            method = visitClass.getMethod(methodName);//只能获取无参数方法
        }
        else{
            Class[] classArgs = new Class[args.length];
            for(int i=0;i<args.length;++i){
                classArgs[i] = args[i].getClass();
                //拿到参数的class,前提参数是个对象，所以最好所有参数都定义为包装类，避免一些不必要的麻烦
            }
            method = visitClass.getMethod(methodName,classArgs);//获取有参数方法
        }

    }

    @After("execution(* ssm_maven.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        //计算访问时间
        long time = new Date().getTime()-startTime.getTime();

        String url = "";
        //获取url，由两部分组成：类上的@RequestMapping和方法上的@RequestMapping

        //访问LogAop类和SysLogController类时不记录日志
        if(visitClass!=null&&method!=null&&visitClass!=LogAop.class&&visitClass!=SysLogController.class){

            //获取类上的@RequestMapping对象
            RequestMapping classAnnotation = (RequestMapping) visitClass.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();

                //获取方法上的@RequestMapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];

                    //全部放在if中，如果某一项为Null,就不提交日志
                    //获取访问的IP地址，通过request来获取，需要在web.xml中配置一个Listener:RequestContextListener
                    String ip = request.getRemoteAddr();

                    //获取当前访问的用户的用户名
                    //通过spring-security的SecurityContextHolder来获取
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();//spring-security中的User
                    String username = user.getUsername();

                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(startTime);
                    sysLog.setUsername(username);
                    sysLog.setMethod("[类名] "+visitClass.getName()+"[方法名] "+method.getName());

                    sysLogService.save(sysLog);
                }
            }
        }


    }

}
