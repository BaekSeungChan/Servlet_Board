package com.example.servelet_board.board.controller;

import com.example.servelet_board.board.dto.BoardDTO;
import com.example.servelet_board.board.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BoardController{

    BoardService boardService = new BoardService();

    public String main(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/board/main.jsp").forward(req,res);
        return "main";
    }

    public String list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         String searchkey = req.getParameter("searchKey");
         List<BoardDTO> list = boardService.boardFindAll(searchkey);

         req.setAttribute("list", list);

        return "list";
    }

    public String view(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Long ID = Long.parseLong(req.getParameter("ID"));
        BoardDTO boardDTO = boardService.boardDetail(ID);

        req.setAttribute("board", boardDTO);

        return "view";
    }

    public String delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long ID = Long.parseLong(req.getParameter("ID"));
        boardService.boardDelete(ID);

        return "redirect:board.do?action=list";
    }

    public String updateForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Long ID = Long.parseLong(req.getParameter("ID"));

        BoardDTO boardDTO = boardService.boardDetail(ID);

        req.setAttribute("board", boardDTO);

        return "updateForm";
    }

    public String update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

    public String insertForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "insertForm";
    }

    public String insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writer = req.getParameter("writer");
        HttpSession httpSession = req.getSession();
        String userid = (String) httpSession.getAttribute("userid");

        BoardDTO boardDTO = BoardDTO.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .userid(userid)
                .build();

        boardService.boardInsert(boardDTO);
        return "redirect:board.do?action=list";
    }
}
