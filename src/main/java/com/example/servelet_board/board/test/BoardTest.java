package com.example.servelet_board.board.test;



import com.example.servelet_board.board.dao.BoardDAO;
import com.example.servelet_board.board.dao.BoardVO;
import com.example.servelet_board.board.util.DBConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;


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

        int result = boardDAO.insert(boardVO);

        System.out.println(result);
    }

}