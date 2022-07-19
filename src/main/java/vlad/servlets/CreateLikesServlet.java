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
            Long currentUserId = null;
            List<Long> unLikedUserIds = null;
            List<User> unLikedUsers = null;
            if (optCookieId.isPresent()) {
                currentUserId = Long.parseLong(optCookieId.get().getValue());
                unLikedUserIds = userService.findUnlikedUserIds(currentUserId);
                final long excludeId = currentUserId;
                unLikedUsers = userService.findSelectedUsers(unLikedUserIds);
                unLikedUsers = unLikedUsers.stream().filter((u)->u.getId() != excludeId).collect(Collectors.toList());
                params.put("unLikedUsers", unLikedUsers);
            }

        templateEngine.render("like-page_test.ftl", params, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){

    }
}
