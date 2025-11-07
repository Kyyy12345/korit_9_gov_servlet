package com.korit.servlet_study.ch09;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/study/students")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");

        servletResponse.setContentType("application/json");

        filterChain.doFilter(servletRequest, servletResponse); // 넘겨줘야 다음 서블릿으로 넘어감 이걸 그대로 서블릿에 전달
    }
}
