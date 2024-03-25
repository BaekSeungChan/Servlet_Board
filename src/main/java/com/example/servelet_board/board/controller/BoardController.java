package com.example.servelet_board.board.controller;

import com.example.servelet_board.board.dto.BoardDTO;
import com.example.servelet_board.board.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            if (!res.isCommitted()) {
                req.getRequestDispatcher("/WEB-INF/views/board/" + jspPage + ".jsp").forward(req, res);
            }
        }
    }


    private String list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         String searchkey = req.getParameter("searchKey");
         List<BoardDTO> list = boardService.boardFindAll(searchkey);

         req.setAttribute("list", list);

        return "list";
    }

    private String view(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Long ID = Long.parseLong(req.getParameter("ID"));
        BoardDTO boardDTO = boardService.boardDetail(ID);

        req.setAttribute("board", boardDTO);

        return "view";
    }

    private String delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long ID = Long.parseLong(req.getParameter("ID"));
        boardService.boardDelete(ID);

        return "redirect:board.do?action=list";
    }

    private String updateForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long ID = Long.parseLong(req.getParameter("ID"));

        BoardDTO boardDTO = boardService.boardDetail(ID);

        req.setAttribute("board", boardDTO);

        return "updateForm";
    }

    private String update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long ID = Long.parseLong(req.getParameter("ID"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writer = req.getParameter("writer");

        BoardDTO boardDTO = BoardDTO.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();

        boardService.boardUpdate(boardDTO, ID);

        return "redirect:board.do?action=list";
    }

    private String insertForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "insertForm";
    }

    private String insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writer = req.getParameter("writer");

        BoardDTO boardDTO = BoardDTO.builder()
                .title(title)
                .content(content)
                .writer(writer).build();

        boardService.boardInsert(boardDTO);
        return "redirect:board.do?action=list";
    }
}
