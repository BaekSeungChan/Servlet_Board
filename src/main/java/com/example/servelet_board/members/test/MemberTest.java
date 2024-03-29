package com.example.servelet_board.members.test;

import com.example.servelet_board.members.dao.MemberDAO;
import com.example.servelet_board.members.dao.MemberVO;
import com.example.servelet_board.members.dto.MemberDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        memberDAO.signUp(memberVO, str);
    }

    @Test
    public void selectAll() {
        List<MemberDTO> members = memberDAO.selectAll();

        for(var member : members){
            System.out.println(member);
        }
    }

    @Test
    public void detailMember(){
        String userid = "qortmdcks97";
        MemberDTO memberDTO  =  memberDAO.detailMember(userid);

        System.out.println(memberDTO);
    }
}
