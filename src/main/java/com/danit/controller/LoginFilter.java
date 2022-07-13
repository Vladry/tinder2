package com.danit.controller;

import com.danit.dao.User;
import com.danit.service.CookieUtil;
import com.danit.service.UserService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    public static final String USER_PARAM_ID = "id";
    private TemplateEngine templateEngine;
    private UserService userService;

    public LoginFilter(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Set<String> allowedUrls = Set.of("/registration", "/assets");
        if (allowedUrls.contains(request.getServletPath()) ) {
            chain.doFilter(request, response);
        }
        Optional<Cookie> optionalCookie = CookieUtil.getCookieByName(request, USER_PARAM_ID);// "id"
//        if (request.getSession(false) == null) {
        if (optionalCookie.isEmpty()) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            User user = userService.findByLoginPass(login, password);
            if (user != null) {
//                HttpSession session = request.getSession();
//                session.setAttribute("id", user.getId());
                response.addCookie(new Cookie(USER_PARAM_ID, String.valueOf(user.getId())));
                chain.doFilter(request, response);
//                request.getRequestDispatcher("/hello").forward(request, response);
            }
            //request.getRequestDispatcher("/login").forward(request, response);
            templateEngine.render("index.ftl", new HashMap<>(), response);
        } else {
            chain.doFilter(request, response);
        }
    }


}
