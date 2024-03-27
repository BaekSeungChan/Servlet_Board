package com.example.servelet_board.board.servlet;

import com.example.servelet_board.board.controller.BoardController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardServlet extends HttpServlet {

    BoardController boardController = new BoardController();

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
            case "list" -> boardController.list(req, res);
            case "view" -> boardController.view(req, res);
            case "delete" -> boardController.delete(req, res);
            case "updateForm" -> boardController.updateForm(req, res);
            case "update" -> boardController.update(req, res);
            case "insertForm" -> boardController.insertForm(req, res);
            case "insert" -> boardController.insert(req, res);
            case "main" -> boardController.main(req, res);
            default -> "";
        };

        if(jspPage.startsWith("redirect:")){
            res.sendRedirect(jspPage.substring("redirect:".length()));
        } else {
            System.out.println(jspPage);
            if (!res.isCommitted()) {
                req.getRequestDispatcher("/WEB-INF/views/board/" + jspPage + ".jsp").forward(req, res);
            }
        }
    }
}
