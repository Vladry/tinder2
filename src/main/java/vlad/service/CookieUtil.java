package vlad.service;

import org.eclipse.jetty.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieUtil {
    public static Optional<Cookie> getCookieByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null || StringUtil.isEmpty(name)) {
            return Optional.empty();
        }
        return Arrays.stream(request.getCookies()).filter(c -> name.equals(c.getName())).findAny();
    }
}
