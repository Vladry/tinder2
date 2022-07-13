package com.danit.controller;

import com.danit.dao.User;
import com.danit.service.CookieUtil;
import com.danit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

import static com.danit.controller.LoginFilter.USER_PARAM_ID;

/**
 * http://localhost:8088/upload
 */
public class UploadServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;

    public UploadServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        templateEngine.render("upload.ftl", resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Optional<Cookie> cookieByName = CookieUtil.getCookieByName(request, USER_PARAM_ID);
            if (cookieByName.isEmpty()) {
                response.sendRedirect("/login");
            }
            Long userId = Long.parseLong(cookieByName.get().getValue());
            User user = userService.read(userId);
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                String fileName = part.getSubmittedFileName();

                InputStream inputStream = part.getInputStream();
                userService.uploadImage(user.getId(), inputStream);
            }
            request.getRequestDispatcher("/users").forward(request, response);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
