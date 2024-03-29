package com.example.servelet_board.board.controller;

import com.example.servelet_board.board.dto.BoardDTO;
import com.example.servelet_board.board.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
//import com.example.servelet_board.board.dto.BoardDTO;boardport javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardController{

    BoardService boardService = new BoardService();

    public Object main(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {
        return "main";
    }

    public Object list(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {
         String searchkey = req.getParameter("searchKey");
         List<BoardDTO> list = boardService.boardFindAll(searchkey);

         req.setAttribute("list", list);

        return "list";
    }

    public Object view(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {

        BoardDTO boardDTO = boardService.boardDetail(board.getId());
        req.setAttribute("board", boardDTO);

        return "view";
    }

    public Object delete(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {
        int updated = boardService.boardDelete(board.getId());

        System.out.println("deleteUpdated " + updated);
        Map<String, Object> map = new HashMap<>();
        if(updated == 1){
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "회원 정보 삭제 실패하였습니다.");
        }
        return map;
    }

    public Object updateForm(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {

        BoardDTO boardDTO = boardService.boardDetail(board.getId());

        return "updateForm";
    }


    public Object update(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {

        int updated  = boardService.boardUpdate(board, board.getId());

        Map<String, Object> map = new HashMap<>();

        if(updated == 1){
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "회원 가입이 실패하였습니다.");
        }

        return map;
    }

    public Object insertForm(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {
        return "insertForm";
    }

    public Object insert(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>();

        if(board.getUserid() == null || board.getUserid().length() == 0){
            map.put("status", -1);
            map.put("statusMessage", "사용자 아이디는 null이거나 길이가 0인 문자열을 사용할 수 없습니다.");
        } else {
            int updated = boardService.boardInsert(board);

            if(updated == 1){
                map.put("status", 0);
            } else {
                map.put("status", -99);
                map.put("statusMessage", "회원 가입이 실패하였습니다.");
            }
        }
        return map;
    }
}
