package com.danit.controller;

import com.danit.service.CookieUtil;
import com.danit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.danit.controller.LoginFilter.USER_PARAM_ID;

public class LogoutServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    public LogoutServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getSession().invalidate();
        CookieUtil.getCookieByName(request, USER_PARAM_ID)
                .ifPresent(c -> {
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                });

        templateEngine.render("index.ftl", response);
//        request.getRequestDispatcher("/hello").forward(request, response);
    }
}
