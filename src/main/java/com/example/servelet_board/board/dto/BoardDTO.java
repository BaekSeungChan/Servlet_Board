package com.example.servelet_board.board.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDate dueDate;
    private String userid;

    private String action;
}