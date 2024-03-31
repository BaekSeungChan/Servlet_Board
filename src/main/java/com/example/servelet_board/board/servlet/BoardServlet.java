package com.example.servelet_board.board.servlet;

import com.example.servelet_board.board.controller.BoardController;
import com.example.servelet_board.board.dto.BoardDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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


    private Map<String, Object> convertMap(Map<String, String[]> map){
        Map<String, Object> result = new HashMap<>();

        for(var entry : map.entrySet()){
            if(entry.getValue().length == 1){
                result.put(entry.getKey(), entry.getValue()[0]);
            } else {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    private void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");


        String contentType = req.getContentType();
        System.out.println("contentType " + contentType);

        ObjectMapper objectMapper = new ObjectMapper();

        BoardDTO boardDTO = null;

        System.out.println("chan " + boardDTO );

        if(contentType == null || contentType.startsWith("application/x-www-form-urlencoded")){
            boardDTO = objectMapper.convertValue(convertMap(req.getParameterMap()), BoardDTO.class);
        } else if(contentType.startsWith("application/json")){
            boardDTO = objectMapper.readValue(req.getInputStream(), BoardDTO.class);
            System.out.println("1111" + boardDTO);

        } else {
            System.out.println("nothing");
        }

        String action = boardDTO.getAction();

        System.out.println("dkdkd " + action);

        Object result = switch(action) {
            case "list" -> boardController.list(req, boardDTO);
            case "view" -> boardController.view(req, boardDTO);
            case "delete" -> boardController.delete(req, boardDTO);
            case "update" -> boardController.update(req, boardDTO);
            case "updateForm" -> boardController.updateForm(req, boardDTO);
            case "insertForm" -> boardController.insertForm(req, boardDTO);
            case "insert" -> boardController.insert(req, boardDTO);
            case "main" -> boardController.main(req, boardDTO);
            default -> "";
        };

        if(result instanceof Map map){
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().append(objectMapper.writeValueAsString(map));
        } else if(result instanceof String url){
            if (url.startsWith("redirect:")){
                res.sendRedirect(url.substring("redirect:".length()));
            }
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/" + url + ".jsp");
            rd.forward(req,res);
        }
    }
}
