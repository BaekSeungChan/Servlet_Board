package com.example.servelet_board.members.servlet;

import com.example.servelet_board.members.controller.MemberController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {

    MemberController memberController = new MemberController();


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

        String jspPage = null;

        if (action != null) {
            jspPage = switch (action) {
                case "signUpPage" -> memberController.signUpPage(req, res);
                case "membersignUp" -> memberController.membersignUp(req, res);
                case "LoginPage" -> memberController.LoginPage(req, res);
                case "Login" -> memberController.login(req, res);
                case "adminPage" -> memberController.adminPage(req, res);
                case "signOut" -> memberController.signOut(req, res);
                case "myPage" -> memberController.myPage(req, res);
                case "updateProfile" -> memberController.updateProfile(req, res);
                case "delete" -> memberController.adminDelete(req, res);
                default -> "";
            };
        }

        if (jspPage != null && jspPage.startsWith("redirect:")) {
            res.sendRedirect(jspPage.substring("redirect:".length()));
        } else if (jspPage != null && !jspPage.isEmpty()) {
            req.getRequestDispatcher("/WEB-INF/views/members/" + jspPage + ".jsp").forward(req, res);
        }
    }
}