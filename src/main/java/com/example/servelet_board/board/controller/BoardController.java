package com.example.servelet_board.board.controller;

import com.example.servelet_board.board.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/board")
public class BoardController extends HttpServlet {

    BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doService(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doService(req, res);
    }

    private void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");

        String jspPage = switch(action) {
            case "list" -> list(req, res);
            case "view" -> view(req, res);
            case "delete" -> delete(req, res);
            case "updateForm" -> updateForm(req, res);
            case "update" -> update(req, res);
            case "insertForm" -> insertForm(req, res);
            case "insert" -> insert(req, res);
            default -> "";
        };

        if(jspPage.startsWith("redirect:")){
            res.sendRedirect(jspPage.substring("redirect:".length()));
        } else {
            System.out.println(jspPage);
            req.getRequestDispatcher("/WEB-INF/views/board/" + jspPage + ".jsp").forward(req,res);
        }
    }


    private String list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "list";
    }

    private String view(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "view";
    }

    private String delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "delete";
    }

    private String updateForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "updateForm";
    }

    private String update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "update";
    }

    private String insertForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "insertForm";
    }

    private String insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "redirect:board?action=list";
    }
}
