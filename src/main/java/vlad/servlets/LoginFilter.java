package vlad.servlets;


import vlad.controller.TemplateEngine;
import vlad.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class LoginFilter implements Filter {
    public static final String USER_PARAM_ID = "id";
    private TemplateEngine templateEngine;
    private UserService userService;

    public LoginFilter(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Set<String> allowedUrls = Set.of("/registration", "/assets");
        if (allowedUrls.contains(request.getServletPath()) ) {
            chain.doFilter(request, response);
        }
    }


}
