package pl.coderslab.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/app/*")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        ServletContext cont = request.getServletContext();
        Boolean _authorized = (Boolean) cont.getAttribute("Authorized");
        if (_authorized != null && _authorized) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }
}
