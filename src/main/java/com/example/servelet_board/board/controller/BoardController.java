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
         String searchKey = board.getSearchKey();
         List<BoardDTO> list = boardService.boardFindAll(searchKey);

         req.setAttribute("list", list);

        return "list";
    }

    public Object view(HttpServletRequest req, BoardDTO boardDTO) throws ServletException, IOException {
        Long getId = Long.parseLong(String.valueOf(boardDTO.getId()));
        BoardDTO board = boardService.boardDetail(getId);

        req.setAttribute("board", board);
        return "view";
    }

    public Object delete(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {
        int updated = boardService.boardDelete(board.getId());

        Map<String, Object> map = new HashMap<>();

        if(updated == 1){
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "회원 정보 삭제 실패하였습니다.");
        }
        return map;
    }

    public Object updateForm(HttpServletRequest req, BoardDTO boardDTO) throws ServletException, IOException {
        Long getId = Long.parseLong(String.valueOf(boardDTO.getId()));
        BoardDTO board = boardService.boardDetail(getId);

        req.setAttribute("board", board);

        return "updateForm";
    }


    public Object update(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {

        System.out.println("1111 " + board);
        System.out.println("1111 " + board.getId());
        int updated  = boardService.boardUpdate(board, board.getId());

        Map<String, Object> map = new HashMap<>();

        System.out.println("dkdkdkdkaaa" + updated);
        if(updated == 1){
            map.put("status", 0);
        } else {
            map.put("status", -99);
            map.put("statusMessage", "수정이 실패하였습니다.");
        }

        return map;
    }

    public Object insertForm(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {
        return "insertForm";
    }

    public Object insert(HttpServletRequest req, BoardDTO board) throws ServletException, IOException {
        int updated = boardService.boardInsert(board);

        Map<String, Object> result = new HashMap<>();

        if (updated == 1) {
            result.put("status", 0);
        } else {
            result.put("status", -99);
            result.put("errorMessage", "글 등록에 실패했습니다. 다시 시도해주세요.");
        }

        return result;
    }

}

