package vlad.servlets;

import vlad.controller.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginFilter implements Filter {

    private String email;
    private String id;
    private final TemplateEngine templateEngine;

    public LoginFilter(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Map<String, Object> params = new HashMap<>();
        String path = req.getServletPath();
        Cookie[] cookies = req.getCookies();
        System.out.println("servletPath(): " + req.getServletPath());

        Set<String> allowedUrls = Set.of("/registration", "/login", "/assets");
        if(allowedUrls.contains(path)) filterChain.doFilter(req, resp);

        Cookie cookieId = Arrays.stream(cookies).filter((cookie)->"id".equals(cookie.getName())).findFirst().orElse(null);
        if (cookieId != null && cookieId.getValue() != null) {
            filterChain.doFilter(req, resp);
            req.getRequestDispatcher(path).forward(req, resp);
        }else {
            templateEngine.render("login.ftl", params, resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
