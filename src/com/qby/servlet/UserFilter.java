package com.qby.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author qby
 * @date 2020/6/16 10:31
 */
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // 过滤请求
        System.out.println("UserFilter。。。doFilter。。。");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
