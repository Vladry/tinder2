package vlad.servlets;

import vlad.NoDataFoundException;
import vlad.controller.TemplateEngine;
import vlad.domain.User;
import vlad.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class RegistrationServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;
    public int counter=1;

    public RegistrationServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoDataFoundException {
        Map<String, Object> params = new HashMap<>();
        templateEngine.render("/registration.ftl", params, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("processed")!= null) return;
        System.out.println("in doPost");
        req.setAttribute("processed", true);
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String avatar = req.getParameter("avatar");
        userService.createUser(name, login, email, avatar);
        Map<String, Object> params = new HashMap<>();


        params.put("likedUsers", null);

        templateEngine.render("userProfile.ftl", params, resp);
    }
}
