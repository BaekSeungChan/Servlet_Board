package com.example.servelet_board.members.controller;

import com.example.servelet_board.members.dao.MemberDAO;
import com.example.servelet_board.members.dao.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member")
public class MemberController extends HttpServlet {

    MemberDAO memberDAO = new MemberDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    private void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        System.out.println("SignUp = " + action);

        String jspPage = null;

        if (action != null) {
            jspPage = switch (action) {
                case "signUpPage" -> signUpPage(req, res);
                case "membersignUp" -> membersignUp(req, res);
                case "LoginPage" -> LoginPage(req, res);
                case "Login" -> Login(req, res);
                default -> "";
            };
        }

        if (jspPage != null && jspPage.startsWith("redirect:")) {
            res.sendRedirect(jspPage.substring("redirect:".length()));
        } else if (jspPage != null && !jspPage.isEmpty()) {
            System.out.println(jspPage);
            req.getRequestDispatcher("/WEB-INF/views/members/" + jspPage + ".jsp").forward(req, res);
        }
    }

    private String signUpPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "SignUpPage";
    }

    private String LoginPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "LoginPage";
    }


    private String Login(HttpServletRequest req, HttpServletResponse res) {
        String jspPage = null;

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String str = username + password;

        HttpSession session = req.getSession();

        session.setAttribute("loginInfo", str);

        jspPage = "redirect:/board.do?action=main";

        return  jspPage;
    }

    private String membersignUp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String jspPage = null;

        String userid = req.getParameter("userid");
        String password1 = req.getParameter("userpassword1");
        String password2 = req.getParameter("userpassword2");
        String username = req.getParameter("username");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");

        MemberVO memberVO = MemberVO.builder()
                .userid(userid)
                .userpassword(password1)
                .username(username)
                .address(address)
                .phone(phone)
                .gender(gender)
                .build();

        String[] hobbies = req.getParameterValues("hobby");

        if (password2 != null && password2.equals(password1)) {
            memberDAO.insert(memberVO, hobbies);
            jspPage = "redirect:/board.do?action=list";
        } else {
            req.getRequestDispatcher("/WEB-INF/views/members/LoginError.jsp").forward(req, res);
        }

        return jspPage;
    }
}
