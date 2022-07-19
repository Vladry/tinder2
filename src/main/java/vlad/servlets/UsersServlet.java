package vlad.servlets;

import vlad.controller.TemplateEngine;
import vlad.domain.User;
import vlad.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;

    public UsersServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        Map<String, Object> params = new HashMap<>();
        Long currentUserId = 1L;
        List<Long> likedUserIds = userService.findLikedUserIds(currentUserId);
        //---------------------
        Set<User> likedUsers = userService.findLikedUsers(likedUserIds);
        params.put("likedUsers", likedUsers);
//        System.out.println("params.get(\"likedUsers\"): " + params.get("likedUsers"));
        templateEngine.render("users.ftl", params, resp);

    }
}
