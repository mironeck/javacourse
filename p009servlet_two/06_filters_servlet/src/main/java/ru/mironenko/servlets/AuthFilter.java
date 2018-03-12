package ru.mironenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;


            if (request.getRequestURI().contains("/signin")) {
                filterChain.doFilter(req, resp);
            } else {
                HttpSession session = request.getSession();
                synchronized (session) {
                    if (session.getAttribute("login") == null) {
//                        ((HttpServletResponse) resp).sendRedirect(String.format("%s/signin", request.getContextPath()));
//                        return;
                    }
                }
                filterChain.doFilter(req, resp);
            }

    }

        @Override
        public void destroy () {

        }
    }
