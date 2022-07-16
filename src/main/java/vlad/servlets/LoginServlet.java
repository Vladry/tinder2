package vlad.servlets;

import vlad.NoDataFoundException;
import vlad.domain.User;
import vlad.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    UserService userService;

    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        Optional<User> user = userService.findByLogin(login);
        user.ifPresent(value -> System.out.println("logged in user: " + value));
        user.orElseThrow(() -> new NoDataFoundException("login", login));
    }
}
