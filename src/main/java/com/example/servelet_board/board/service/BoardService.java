package com.example.servelet_board.board.service;

import com.example.servelet_board.board.dao.BoardDAO;
import com.example.servelet_board.board.dao.BoardVO;
import com.example.servelet_board.board.dto.BoardDTO;
import com.example.servelet_board.board.util.MapperUtil;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BoardService {
    BoardDAO boardDAO = new BoardDAO();
    ModelMapper modelMapper = MapperUtil.INSTANCE.get();

    public int boardInsert(BoardDTO boardDTO){
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);
        int updated = boardDAO.boardInsert(boardVO);
        return updated;
    }

    public List<BoardDTO> boardFindAll(){
        List<BoardVO> boards = boardDAO.boardFindAll();

        return boards.stream().map(vo -> modelMapper.map(vo, BoardDTO.class))
                .collect(Collectors.toList());

    }

    public BoardDTO boardDetail(Long id){
        BoardVO boardVO = boardDAO.boardDetail(id);

        BoardDTO boardDTO = modelMapper.map(boardVO, BoardDTO.class);

        return boardDTO;
    }

    public int boardDelete(Long id){
        int updated = boardDAO.boardDelete(id);

        return updated;
    }

    public int boardUpdate(BoardDTO boardDTO, Long id) {
        BoardVO boardVO = modelMapper.map(boardDTO, BoardVO.class);

        return boardDAO.boardUpdate(boardVO, id);
    }
}