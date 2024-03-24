package com.example.servelet_board.board.test;



import com.example.servelet_board.board.dao.BoardDAO;
import com.example.servelet_board.board.dao.BoardVO;
import com.example.servelet_board.board.util.DBConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;


public class BoardTest {

    BoardDAO boardDAO = new BoardDAO();

    // 데이터베이스 연결
    @Test
    public void databaseTest(){
        Connection conn = DBConnectionUtil.DATABASE.getConnection();

        Assertions.assertNotNull(conn);
    }


    @Test
    public void insertTest(){
        BoardVO boardVO = BoardVO.builder()
                .title("chan")
                .content("good")
                .writer("백승찬")
                .build();

        int result = boardDAO.boardInsert(boardVO);

        System.out.println(result);
    }


    @Test
    public void boardFindAll(){
        String searchkey = "";
        List<BoardVO> list = boardDAO.boardFindAll(searchkey);

        for(var board : list){
            System.out.println(board);
        }
    }

    @Test
    public void boardDetail(){
        BoardVO boardVO  = boardDAO.boardDetail(1L);

        System.out.println(boardVO);
    }

    @Test
    public void boardDelete(){
        int updated = boardDAO.boardDelete(7L);
        System.out.println(updated);
    }

    @Test
    public void boardUpdate(){
        Long id = 8L;

        BoardVO boardVO = BoardVO.builder()
                .title("update")
                .content("test222211111")
                .writer("백승찬")
                .build();

        int result = boardDAO.boardUpdate(boardVO, id);

        System.out.println(result);
    }


}