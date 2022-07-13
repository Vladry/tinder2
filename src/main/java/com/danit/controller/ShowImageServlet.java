package com.danit.controller;

import com.danit.dao.User;
import com.danit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class ShowImageServlet extends HttpServlet {
    private UserService userService;

    public ShowImageServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long userId = Long.parseLong(req.getParameter("id"));
            resp.setContentType("image/jpeg");
            BufferedOutputStream bos = new BufferedOutputStream( resp.getOutputStream( ) );
            byte[] image = userService.getImage(userId);
            if (image != null) {
                bos.write(image);
                bos.flush();
                bos.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
