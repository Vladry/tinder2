package vlad.servlets;

import vlad.controller.TemplateEngine;
import vlad.domain.User;
import vlad.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;


public class LoginServlet extends HttpServlet {

    private final TemplateEngine templateEngine;
    private final UserService userService;
    private User user;

    public LoginServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    Consumer<User> setUser = (usr) -> {
        System.out.println("logged in " + usr);
        this.user = usr;
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> params = new HashMap<>();
        String login = req.getParameter("login");
        Optional<User> optUser = userService.findByLogin(login);

        optUser.ifPresentOrElse(
                (u) -> setUser.accept(u),
                () -> {
                    final String msg = "user not found in database!";
                    System.out.println(msg);
                    params.put("message", msg);
                    this.user = null;
                });

        Cookie cookieId = new Cookie("id", String.valueOf(this.user.getId()));
        cookieId.setMaxAge(60*60);
        resp.addCookie(cookieId);
        params.put("user", this.user);
        templateEngine.render("userProfile.ftl", params, resp);
    }
}
