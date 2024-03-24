package com.example.servelet_board.board.dao;

import com.example.servelet_board.board.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class BoardDAO {

    // 데이터베이스 연결
    private static Connection conn = DBConnectionUtil.DATABASE.getConnection();
    private static PreparedStatement boardInsert = null;

    static {
        try {
            boardInsert = conn.prepareStatement("insert into boards (title, content, writer, dueDate) values (?, ?, ?, ?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert (BoardVO boardVO){
        int  updated = 0;
        try {
            boardInsert.setString(1, boardVO.getTitle());
            boardInsert.setString(2, boardVO.getContent());
            boardInsert.setString(3, boardVO.getWriter());
            boardInsert.setDate(4, Date.valueOf(LocalDate.now()));

            updated = boardInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

}