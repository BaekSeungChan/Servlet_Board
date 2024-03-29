package com.example.servelet_board.board.dao;

import com.example.servelet_board.util.DBConnectionUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    // 데이터베이스 연결
    private static Connection conn = DBConnectionUtil.DATABASE.getConnection();
    private static PreparedStatement boardInsert = null;
    private static PreparedStatement boardFindAll =  null;
    private static PreparedStatement boardSearch =  null;
    private static PreparedStatement boardDetail =  null;
    private static PreparedStatement boardDelete =  null;
    private static PreparedStatement boardUpdate = null;

    static {
        try {
            boardInsert = conn.prepareStatement("insert into boards (title, content, writer, dueDate,userid) values (?, ?, ?, ?,?)");
            boardFindAll = conn.prepareStatement("select * from boards");
            boardSearch = conn.prepareStatement("select * from boards where title like ? ");
            boardDetail = conn.prepareStatement("select * from boards where id = ?");
            boardDelete = conn.prepareStatement("delete from boards where id = ?");
            boardUpdate = conn.prepareStatement("update boards set title = ? , content = ?, dueDate = ? where id = ?");

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
            boardInsert.setString(5, boardVO.getUserid());

            updated = boardInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // 특정 검색 or 전체 검색
    public List<BoardVO> boardFindAll(String searchKey) {
        List<BoardVO> list = new ArrayList<>();

        try{
            ResultSet rs = null;

            if(searchKey !=null && !searchKey.isEmpty()){
                boardSearch.setString(1, "%" + searchKey + "%");
                rs = boardSearch.executeQuery();

            } else {
                rs = boardFindAll.executeQuery();
            }

            while (rs.next()){
                BoardVO boardVO = BoardVO.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .writer(rs.getString("userid"))
                        .dueDate(rs.getDate("dueDate").toLocalDate())
                        .build();

                list.add(boardVO);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }


    public BoardVO boardDetail(Long id){
        BoardVO boardVO = null;
        try{
            boardDetail.setLong(1, id);
            ResultSet rs = boardDetail.executeQuery();

            if(rs.next()){
                boardVO = BoardVO.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .writer(rs.getString("userid"))
                        .dueDate(rs.getDate("dueDate").toLocalDate())
                        .build();
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return boardVO;

    }



    public int boardDelete(Long id){
        int updated = 0;

        try{
            boardDelete.setLong(1, id);

            updated = boardDelete.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return updated;
    }

    public int boardUpdate(BoardVO boardVO, Long id){
        int updated = 0;

        try{
            boardUpdate.setString(1, boardVO.getTitle());
            boardUpdate.setString(2, boardVO.getContent());
            boardUpdate.setDate(3, Date.valueOf(LocalDate.now()));
            boardUpdate.setLong(4, id);

            updated = boardUpdate.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updated;
    }



}