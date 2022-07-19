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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserProfileServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;

    public UserProfileServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NoDataFoundException {
        Long id = Long.parseLong(req.getParameter("id"));
        Map<String, Object> params = new HashMap<>();
        Optional<User> u = userService.findById(id);
        u.ifPresentOrElse((user)->params.put("user", user), ()-> params.put("message", "user with id: "+ id +"not found"));

        templateEngine.render("userProfile", params, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
