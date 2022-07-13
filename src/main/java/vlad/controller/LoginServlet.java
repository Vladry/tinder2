package vlad.controller;

import vlad.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

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
