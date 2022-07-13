package com.danit.controller;

import com.danit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {
    private UserService userService;
    private TemplateEngine templateEngine;

    public UsersServlet(UserService userService, TemplateEngine templateEngine) {
        this.userService = userService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("users", userService.findAll());
        templateEngine.render("users.ftl", data, resp);
    }
}
