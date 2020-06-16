package com.qby.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听项目的启动 停止
 * @author qby
 * @date 2020/6/16 11:01
 */
public class UserListener implements ServletContextListener {

    // 监听 ServletContext 销毁
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("UserListener contextInitialized");
    }

    // 监听 ServletContext 启动初始化
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println("UserListener contextDestroyed");
    }
}
