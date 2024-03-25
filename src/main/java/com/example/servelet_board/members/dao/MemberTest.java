package com.example.servelet_board.members.dao;

import org.junit.jupiter.api.Test;

public class MemberTest {

    MemberDAO memberDAO = new MemberDAO();

    @Test
    public void insert(){
        MemberVO memberVO = MemberVO.builder()
                .userid("bsc741386")
                .userpassword("1234")
                .username("승찬")
                .address("성남시")
                .phone("010-0000-0000")
                .gender("남")
                .build();

        String[] str = new String[]{"축구", "농구", "야구"};
        memberDAO.insert(memberVO, str);
    }
}
