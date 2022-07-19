package vlad.servlets;

import vlad.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LogOutServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Arrays.stream(req.getCookies()).filter((cookie) -> cookie.getName().equals("id"))
                .peek((cookie) -> cookie.setMaxAge(0)).findFirst().ifPresent(resp::addCookie);
    }
}
