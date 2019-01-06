package cn.sp.component;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

/**
 * Aspect切面类
 * Created by 2YSP on 2018/6/7.
 */
//@Component
//@Aspect
public class WebControllerAop {

    private Gson gson = new GsonBuilder().create();

    //匹配cn.sp.controller包及其子包下的所有类的所有方法
    @Pointcut("execution(* cn.sp.controller..*.*(..))")
    public void executeService(){

    }

    /**
     * 前置通知，方法调用前被调用
     * @param point
     */
    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint point){
        System.out.println("我是前置通知");
        //获取目标方法的参数信息
        Object[] args = point.getArgs();
        //AOP代理类的信息
        point.getThis();
        //代理的目标对象
        point.getTarget();
        //用的最多 通知的签名
        Signature signature = point.getSignature();
        //代理的是哪一个方法
        System.out.println(signature.getName());
        //AOP代理类的名字
        System.out.println(signature.getDeclaringTypeName());
        //AOP代理类的类信息
        signature.getDeclaringType();
        //获取requestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取HttpServletRequest信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.
                resolveReference(RequestAttributes.REFERENCE_REQUEST);

        //获取session信息
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String,String> parameterMap  = Maps.newHashMap();
        while (parameterNames.hasMoreElements()){

            String param = parameterNames.nextElement();
            parameterMap.put(param,request.getParameter(param));
        }
        String str = gson.toJson(parameterMap);
        if (args.length > 0){
            System.out.println("请求参数为："+str);
        }
    }

    /**
     * 后置返回通知
     * 这里需要注意的是:
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value = "execution(* cn.sp.controller..*.*(..))",returning = "keys")
    public void doAfterReturnAdvice1(JoinPoint joinPoint,Object keys){
        System.out.println("第一个后置返回通知的返回值："+keys);
    }


    @AfterReturning(value = "execution(* cn.sp.controller..*.*(..))",returning = "keys",argNames = "keys")
    public void doAfterReturnAdvice2(String keys){
        System.out.println("第二个后置返回通知的返回值："+keys);
    }


    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *      对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "executeService()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
        System.out.println(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            System.out.println("发生了空指针异常!!!!!");
        }
    }


    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     * @param joinPoint
     */
    @After("executeService()")
    public void doAfterAdvice(JoinPoint joinPoint){

        System.out.println("后置通知执行了!!!!");
    }

    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("execution(* cn.sp.controller..*.testAround*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());
        try {//obj之前可以写目标方法执行前的逻辑
            Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
