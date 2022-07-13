package com.danit.controller;

import com.danit.dao.User;
import com.danit.dao.UserDao;
import com.danit.dao.UserJdbcDao;
import com.danit.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

//@WebServlet(urlPatterns = "/")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    private TemplateEngine templateEngine;

    public LoginServlet(UserService userService, TemplateEngine templateEngine) {
        this.userService = userService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        templateEngine.render("index.ftl", response);
//        request.getRequestDispatcher("/hello").forward(request, response);
    }
}
