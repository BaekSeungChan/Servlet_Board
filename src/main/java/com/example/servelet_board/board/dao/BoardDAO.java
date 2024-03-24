package com.example.servelet_board.board.dao;

import com.example.servelet_board.board.util.DBConnectionUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    // 데이터베이스 연결
    private static Connection conn = DBConnectionUtil.DATABASE.getConnection();
    private static PreparedStatement boardInsert = null;
    private static PreparedStatement boardFindAll =  null;

    static {
        try {
            boardInsert = conn.prepareStatement("insert into boards (title, content, writer, dueDate) values (?, ?, ?, ?)");
            boardFindAll = conn.prepareStatement("select * from boards");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int boardInsert (BoardVO boardVO){
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

    public List<BoardVO> boardFindAll() {
        List<BoardVO> list = new ArrayList<>();

        try{

            ResultSet rs = boardFindAll.executeQuery();

            while (rs.next()){
                BoardVO boardVO = BoardVO.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .writer(rs.getString("writer"))
                        .dueDate(rs.getDate("dueDate").toLocalDate())
                        .build();

                list.add(boardVO);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

}