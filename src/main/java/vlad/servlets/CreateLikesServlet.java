package vlad.servlets;

import vlad.controller.TemplateEngine;
import vlad.domain.User;
import vlad.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

public class CreateLikesServlet extends HttpServlet {
    public final TemplateEngine templateEngine;
    public final UserService userService;

    public CreateLikesServlet(TemplateEngine templateEngine, UserService userService){
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
            Map<String, Object> params = new HashMap<>();
            Cookie[] cookies = req.getCookies();
            Optional<Cookie> optCookieId = Arrays.stream(cookies).filter((c) -> "id".equals(c.getName())).findFirst();
            if (optCookieId.isPresent()) {
                Long currentUserId = Long.parseLong(optCookieId.get().getValue());
                int limit = 200, offset = 0;
                List<User> unLikedUsers = userService.findUnlikedUsers(currentUserId, limit, offset);
                params.put("unLikedUsers", unLikedUsers);
            }

        templateEngine.render("like-page_test.ftl", params, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){

    }
}
