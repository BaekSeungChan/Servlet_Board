package com.example.servelet_board.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/board.do")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        String action = req.getParameter("action");

        if ("insertForm".equals(action)) {
            HttpSession session = req.getSession();
            if (session.getAttribute("userid") == null) {
                res.sendRedirect("/member?action=LoginPage");
                return;
            }
        }

        chain.doFilter(request, response);
    }

}

