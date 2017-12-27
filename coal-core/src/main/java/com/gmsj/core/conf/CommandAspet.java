package com.gmsj.core.conf;

import com.gmsj.core.business.command.base.HeaderCommand;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 创建人： Hehaiyang
 * 包名字： com.gmsj.core.conf
 * 创建时间：2017/12/27
 *  *类名：CommandAspet
 * 描述： 处理系统组的切点
 * Copyright @ 2014-2017
 */
@Aspect
@Component
public class CommandAspet {

    @Value("${sys.group.code}")
    public String GROUP_CODE;

    @Pointcut("@annotation(com.gmsj.core.lib.Command)")
    public void commandPointCut() {
    }

    @Around("commandPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
//        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        //

        for (Object obj : args) {
//            Class<?> clazz =  o.getClass();
//            Field nameField = clazz.getDeclaredField("groupCode");
//            nameField.setAccessible(true);// 设置操作权限为true
//            nameField.set(o, "admin");

            //如果参数是headercommand的实例或者子类实例，则修改其某个属性值
            if (obj instanceof HeaderCommand) {
                HeaderCommand c = (HeaderCommand) obj;
                c.setGroupCode(GROUP_CODE);
            }
        }
        //用改变后的参数执行目标方法
        Object retObj = point.proceed(args);
//        System.out.println("@Around：执行目标方法之后...");
//        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return retObj;
    }
}
