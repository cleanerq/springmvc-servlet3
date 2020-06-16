package com.qby.servlet;

import com.qby.service.HelloService;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author qby
 * @date 2020/6/16 9:24
 */
// 容器启动的时候 会将@HandlesTypes 指定的这个类型下面的子类(实现类 子接口等)传递过来
// 传入感兴趣的类型
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 应用启动时会运行onStartup这个方法
     * 使用ServletContext注册web组件（servlet，filter，listener）
     *
     * @param set 感兴趣的类型的所有子类型
     * @param servletContext 代表当前web应用的 ServletContext 一个web应用一个 ServletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("　感兴趣的类型　");

        for (Class<?> aClass : set) {
            System.out.println(aClass);
        }
        // 注册组件
        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", new UserServlet());
        userServlet.addMapping("/user");

        // 注册listener
        servletContext.addListener(UserListener.class);

        // 注册filter
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
        // 配置filter的映射信息
        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    }
}
