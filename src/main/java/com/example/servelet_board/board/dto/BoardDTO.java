package com.example.servelet_board.board.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDTO {
    @JsonIgnore
    private Long id;

    private String title;
    private String content;
    private String writer;

    @JsonIgnore
    private LocalDate dueDate;

    private String userid;

    private String action;
}