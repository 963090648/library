package com.example.library.interceptor;/**
 * @description:
 * @author: yubin
 * @time: 2023/2/17
 */

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *@program: library
 *@description:
 *@author: yubin
 *@create: 2023-02-17 01:41
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 查看用户session 是否已登录

        return true;
    }
}