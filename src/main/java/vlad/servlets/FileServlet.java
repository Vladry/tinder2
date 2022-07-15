package vlad.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream os = resp.getOutputStream()) {
            String rqName = req.getRequestURI();
            ClassLoader classLoader = this.getClass().getClassLoader();

            byte[] bytes = classLoader.getResourceAsStream(rqName.substring(1)).readAllBytes();
            os.write(bytes);

        } catch (NullPointerException ex) {
            resp.setStatus(404);
        }
    }

}
