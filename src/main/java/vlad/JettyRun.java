package vlad;

import com.zaxxer.hikari.HikariDataSource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import vlad.controller.TemplateEngine;
import vlad.dao.UserJdbcDao;
import vlad.service.UserService;
import vlad.servlets.*;

import javax.servlet.*;
//import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JettyRun {
    public static void main(String[] args) throws Exception {
        String portStr = System.getenv("PORT");
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");
        portStr = portStr == null ? "8088" : portStr;
        int port = Integer.parseInt(portStr);
        System.out.println("PORT: " + port);

        HikariDataSource hDataSource = new HikariConnectionPool().getDataSource();
        UserJdbcDao userJdbcDao = new UserJdbcDao(hDataSource);
        UserService userService = new UserService(userJdbcDao);
        TemplateEngine templateEngine = new TemplateEngine();

        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addFilter(new FilterHolder(new LoginFilter(templateEngine)), "/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
        handler.addServlet(new ServletHolder(new UserProfileServlet(templateEngine, userService)), "/userProfile");
        handler.addServlet(new ServletHolder(new RegistrationServlet(templateEngine, userService)), "/registration");
        handler.addServlet(new ServletHolder(new LoginServlet(templateEngine, userService)), "/login");
        handler.addServlet(new ServletHolder(new LikedServlet(templateEngine, userService)), "/liked");
        handler.addServlet(new ServletHolder(new LogOutServlet()), "/logout");
        handler.addServlet(new ServletHolder(new CreateLikesServlet(templateEngine, userService)), "/create_likes");

        server.setHandler(handler);
        server.start();
        server.join();


    }
}
