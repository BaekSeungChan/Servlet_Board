package com.example.servelet_board.board.service;

import com.example.servelet_board.board.dto.BoardDTO;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BoardServiceTest {

    BoardService boardService = new BoardService();

    @Test
    public void insertTest(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("test7")
                .content("test7")
                .writer("백승찬")
                .build();

        int result = boardService.boardInsert(boardDTO);

        System.out.println(result);
    }

    @Test
    public void boardFindAll(){
        List<BoardDTO> list = boardService.boardFindAll();

        for(var board : list){
            System.out.println(board);
        }
    }

    @Test
    public void boardDetail(){
        BoardDTO boardDTO  = boardService.boardDetail(5L);

        System.out.println(boardDTO);
    }

    @Test
    public void boardDelete(){
        int updated = boardService.boardDelete(5L);
        System.out.println(updated);
    }


    @Test
    public void boardUpdate(){
        Long id = 1L;

        BoardDTO boardDTO = BoardDTO.builder()
                .title("update74447")
                .content("test77")
                .writer("백승찬")
                .build();

        int result = boardService.boardUpdate(boardDTO, id);

        System.out.println(result);
    }

}