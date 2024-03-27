package com.example.servelet_board.members.dao;

import com.example.servelet_board.members.dto.MemberDTO;
import com.example.servelet_board.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private static Connection conn = null;
    private static PreparedStatement memberInsert = null;
    private static PreparedStatement hobbyInsert = null;
    private static PreparedStatement adminMemberAndHobby = null;
    private static PreparedStatement memberNameAndPassWord = null;
    private static PreparedStatement memerDettail = null;

    String jspPage = null;
    static {
        try{
            conn = DBConnectionUtil.DATABASE.getConnection();
            memberInsert = conn.prepareStatement("INSERT INTO members (username, address, phone, gender, userid, userpassword) VALUES (?, ?, ?, ?, ?, ?);");
            memberNameAndPassWord = conn.prepareStatement("SELECT * FROM members where userid = ?;");
            hobbyInsert = conn.prepareStatement("INSERT INTO memberhobby (membernum, hobbyname) VALUES (LAST_INSERT_ID(), ?), (LAST_INSERT_ID(), ?), (LAST_INSERT_ID(), ?);");
            memerDettail = conn.prepareStatement("SELECT m.membernum, m.userid, m.username, m.userpassword, m.address, m.phone, m.gender, GROUP_CONCAT(h.hobbyname SEPARATOR ', ') AS hobbies\n" +
                    "FROM members m\n" +
                    "LEFT JOIN memberhobby mh ON m.membernum = mh.membernum\n" +
                    "LEFT JOIN hobby h ON mh.hobbyname = h.hobbyname\n" +
                    "WHERE m.userid = ? " +
                    "GROUP BY m.membernum;\n");
            adminMemberAndHobby = conn.prepareStatement("SELECT m.membernum, m.userid, m.username, m.userpassword, m.address, m.phone, m.gender, GROUP_CONCAT(h.hobbyname SEPARATOR ', ') AS hobbies\n" +
                    "FROM members m\n" +
                    "LEFT JOIN memberhobby mh ON m.membernum = mh.membernum\n" +
                    "LEFT JOIN hobby h ON mh.hobbyname = h.hobbyname\n" +
                    "GROUP BY m.membernum;\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(MemberVO memberVO, String[] str){
        int member  = 0;
        int hobby  = 0;
        try{
            memberInsert.setString(1, memberVO.getUsername());
            memberInsert.setString(2, memberVO.getAddress());
            memberInsert.setString(3, memberVO.getPhone());
            memberInsert.setString(4, memberVO.getGender());
            memberInsert.setString(5, memberVO.getUserid());
            memberInsert.setString(6, memberVO.getUserpassword());

            member = memberInsert.executeUpdate();

            System.out.println(member);

            hobbyInsert.setString(1, str[0]);
            hobbyInsert.setString(2, str[1]);
            hobbyInsert.setString(3, str[2]);

            hobby = hobbyInsert.executeUpdate();

            System.out.println(hobby);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public MemberDTO detailMember(String userid){
        MemberDTO memberDTO = null;
        try{
            memerDettail.setString(1, userid);

            ResultSet rs = memerDettail.executeQuery();

            System.out.println("susuus" + userid);
            if(rs.next()){
                 memberDTO = MemberDTO.builder()
                        .membernum(rs.getLong("membernum"))
                        .userid(rs.getString("userid"))
                        .userpassword(rs.getString("userpassword"))
                        .username(rs.getString("username"))
                        .address(rs.getString("address"))
                        .phone(rs.getString("phone"))
                        .gender(rs.getString("gender"))
                        .hobby(rs.getString("hobbies"))
                        .build();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return memberDTO;
    }


    public List<MemberDTO> selectAll(){
        List<MemberDTO> list = new ArrayList<>();
        try {
            ResultSet rs = adminMemberAndHobby.executeQuery();


            while (rs.next()){
                MemberDTO memberDTO = MemberDTO.builder()
                        .membernum(rs.getLong("membernum"))
                        .userid(rs.getString("userid"))
                        .userpassword(rs.getString("userpassword"))
                        .username(rs.getString("username"))
                        .address(rs.getString("address"))
                        .phone(rs.getString("phone"))
                        .gender(rs.getString("gender"))
                        .hobby(rs.getString("hobbies"))
                        .build();

                list.add(memberDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }


    public MemberVO LoginCheck(String username){
        MemberVO memberVO = null;

        try {
            memberNameAndPassWord.setString(1, username);

            ResultSet rs = memberNameAndPassWord.executeQuery();

            if(rs.next()){
                memberVO = MemberVO.builder()
                        .userid(rs.getString("userid"))
                        .userpassword(rs.getString("userpassword"))
                        .username(rs.getString("username"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  memberVO;
    }

}
