package com.qby.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qby
 * @date 2020/6/16 18:29
 */
@WebServlet(value = "/async", asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、支持异步处理 asyncSupported = true
        // 2、开启异步模式
        System.out.println("主线程开始：" + Thread.currentThread() + "====>" + System.currentTimeMillis());
        AsyncContext asyncContext = req.startAsync();

        // 3、业务逻辑进行异步处理 开始异步处理
        asyncContext.start(() -> {
            try {
                System.out.println("子线程开始：" + Thread.currentThread() + "====>" + System.currentTimeMillis());
                sayHello();
                asyncContext.complete();
                // 4、获取响应
                ServletResponse response = asyncContext.getResponse();
                response.getWriter().write("hello async....");

                System.out.println("子线程结束：" + Thread.currentThread() + "====>" + System.currentTimeMillis());
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("主线程结束：" + Thread.currentThread() + "====>" + System.currentTimeMillis());
    }


    public void sayHello() throws InterruptedException {

        System.out.println(Thread.currentThread() + " processing.... ");
        Thread.sleep(3000);
    }
}
