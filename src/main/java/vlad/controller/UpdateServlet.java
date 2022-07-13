package vlad.controller;

import vlad.dao.User;
import vlad.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UpdateServlet extends HttpServlet {
    private UserService userService;
    private TemplateEngine templateEngine;

    public UpdateServlet() {}

    public UpdateServlet(UserService userService, TemplateEngine templateEngine) {
        this.userService = userService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> optionalCookie = Optional.ofNullable(req.getParameter("id"));
        if (optionalCookie.isEmpty()) {
            templateEngine.render("login.ftl", Collections.emptyMap(), resp );
            return;
        }
        User user = userService.read(Long.parseLong(optionalCookie.get()));

        Map<String, Object> params = new HashMap<>();
        params.put("submitMapping", "/update");
        params.put("user", user);
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
        User user = userService.findByLoginPass(login, password);
        user.setAge(age);
        user.setGroupId(groupId);
        user.setPassword(password);
        user.setName(name);
        user.setLogin(login);
        userService.update(user);
//        resp.sendRedirect("/users");
        req.getRequestDispatcher("/users").forward(req, resp);
    }
}
