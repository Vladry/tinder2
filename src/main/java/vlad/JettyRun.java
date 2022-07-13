package vlad;

import vlad.controller.FileServlet;
import vlad.controller.HelloServlet;
import vlad.controller.LoginFilter;
import vlad.controller.LoginServlet;
import vlad.controller.LogoutServlet;
import vlad.controller.RegistrationServlet;
import vlad.controller.ShowImageServlet;
import vlad.controller.TemplateEngine;
import vlad.controller.UpdateServlet;
import vlad.controller.UploadServlet;
import vlad.controller.UsersServlet;
import vlad.dao.UserDao;
import vlad.dao.UserJdbcDao;
import vlad.service.DefaultUserService;
import vlad.service.UserService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

public class JettyRun {
    public static void main(String[] args) throws Exception {
        String portStr = System.getenv("PORT");
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String username = System.getenv("JDBC_DATABASE_USERNAME");
        String password = System.getenv("JDBC_DATABASE_PASSWORD");
        portStr = portStr == null ? "8088" : portStr;
        Integer port = Integer.parseInt(portStr);
        System.out.println("PORT: " + port);

        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        final UserDao userDao = new UserJdbcDao();
        UserService userService = new DefaultUserService(userDao);
        TemplateEngine templateEngine = new TemplateEngine();

        // Enabling session support
//        SessionHandler sessionHandler = new SessionHandler();
//        handler.setSessionHandler(sessionHandler);


        handler.addServlet(new ServletHolder(new HelloServlet(templateEngine)), "/hello");
        handler.addServlet(new ServletHolder(new LoginServlet(userService, templateEngine)), "/");
        handler.addServlet(new ServletHolder(new LogoutServlet(templateEngine)), "/logout");
        handler.addServlet(new ServletHolder(new FileServlet()), "/assets/*");
        handler.addFilter(new FilterHolder(new LoginFilter(templateEngine, userService)), "/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addServlet(new ServletHolder(new RegistrationServlet(userService, templateEngine)), "/registration");
        handler.addServlet(new ServletHolder(new UpdateServlet(userService, templateEngine)), "/update");
        handler.addServlet(new ServletHolder(new ShowImageServlet(userService)), "/image");
        handler.addServlet(new ServletHolder(new UsersServlet(userService, templateEngine)), "/users");

        String location = "./tmp";
        ensureDirExists(location);
        ServletHolder servletHolder = new ServletHolder(new UploadServlet(templateEngine, userService));
        handler.addServlet(servletHolder, "/upload");
        servletHolder.getRegistration().setMultipartConfig(new MultipartConfigElement(location));

//        handler.addServlet(new ServletHolder(new MessageServlet(messageDao, userDao)), "/messages/*");
//        handler.addFilter(MessageFilter.class, "/messages/*", EnumSet.of(DispatcherType.REQUEST));


//        handler.addServlet(RedirectServlet.class, "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }

    private static Path ensureDirExists(String dirName) {
        Path dir = Paths.get(dirName).toAbsolutePath();

        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dir;
    }
}
