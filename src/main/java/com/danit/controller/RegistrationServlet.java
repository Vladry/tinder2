package com.danit.controller;

import com.danit.dao.User;
import com.danit.dao.UserDao;
import com.danit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegistrationServlet extends HttpServlet {
    private UserService userService;
    private TemplateEngine templateEngine;

    public RegistrationServlet() {}

    public RegistrationServlet(UserService userService, TemplateEngine templateEngine) {
        this.userService = userService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("submitMapping", "/registration");
        templateEngine.render("registration.ftl", params, resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String ageStr = req.getParameter("age");
        String name = req.getParameter("name");
        String groupIdStr = req.getParameter("group");
        //you need to check user login here..

        int age = 0;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect("/registration");
            return;
        }
        long groupId = 0;
        try {
            groupId = Long.parseLong(groupIdStr);
        } catch (NumberFormatException e) {
//            resp.setStatus(307);
//            resp.addHeader("Location", "/registration");
            resp.sendRedirect("/registration");
            return;
        }
        User user = new User(null, name, age, groupId, login, password);
        userService.create(user);
        resp.sendRedirect("/login");
//        req.getRequestDispatcher("/login").forward(req, resp);
    }
}
