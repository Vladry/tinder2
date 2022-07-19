package vlad.servlets;

import vlad.controller.TemplateEngine;
import vlad.domain.User;
import vlad.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;

    public LikedServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> params = new HashMap<>();
        Cookie[] cookies = req.getCookies();
        Optional<Cookie> optCookieId = Arrays.stream(cookies).filter((c) -> "id".equals(c.getName())).findFirst();
        Long currentUserId = null;
        List<Long> likedUserIds = null;
        List<User> likedUsers = null;
        if (optCookieId.isPresent()) {
            currentUserId = Long.parseLong(optCookieId.get().getValue());
            likedUserIds = userService.findLikedUserIds(currentUserId);
            likedUsers = userService.findSelectedUsers(likedUserIds);
            params.put("likedUsers", likedUsers);
        }
        templateEngine.render("liked.ftl", params, resp);

    }
}
