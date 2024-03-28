package com.example.servelet_board.members.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDTO {
    private Long membernum; //pk
    private String userid; // 아이디
    private String userpassword; // 비빌번호
    private String username; // 이름
    private String address; // 주소
    private String phone; // 전화번호
    private String gender; // 성별
    private String hobby; // 취비
    private String uuid;
}
