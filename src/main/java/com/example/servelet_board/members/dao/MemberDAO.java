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
    private static PreparedStatement adminMemberAndHobby = null;
    private static PreparedStatement memberNameAndPassWord = null;
    private static PreparedStatement memberDetail = null;
    private static PreparedStatement adminDelete = null;
    private static PreparedStatement updateUuid = null;
    private static PreparedStatement useridBoard = null;


    String jspPage = null;
    static {
        try{
            conn = DBConnectionUtil.DATABASE.getConnection();
            memberNameAndPassWord = conn.prepareStatement("SELECT * FROM members where userid = ?;");
            memberDetail = conn.prepareStatement("SELECT m.membernum, m.userid, m.username, m.userpassword, m.address, m.phone, m.gender, GROUP_CONCAT(h.hobbyname SEPARATOR ', ') AS hobbies\n" +
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

            adminDelete = conn.prepareStatement("DELETE FROM members WHERE membernum = ?;");

            updateUuid = conn.prepareStatement("UPDATE members SET uuid = ? WHERE userid = ?");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adminDelete(int membernum){
        try{
            // 회원과 관련된 hobby 레코드 삭제
            PreparedStatement deleteHobby = conn.prepareStatement("DELETE FROM memberhobby WHERE membernum = ?");
            deleteHobby.setInt(1, membernum);
            deleteHobby.executeUpdate();

            // 회원 레코드 삭제
            adminDelete.setInt(1, membernum);
            adminDelete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProfile(MemberVO memberVO, String[] hobby, int membernum) {
        Connection conn = null;
        PreparedStatement hobbyDelete = null;
        PreparedStatement memberUpdate = null;
        PreparedStatement hobbyInsert = null;

        System.out.println("hobby : " + hobby);
        System.out.println("membernum : " + membernum);
        System.out.println("memberVo " + memberVO);

        try {
            conn = DBConnectionUtil.DATABASE.getConnection();
            conn.setAutoCommit(false);

            hobbyDelete = conn.prepareStatement("delete from memberhobby where membernum = ?");
            hobbyDelete.setInt(1, membernum);
            hobbyDelete.executeUpdate();

            memberUpdate = conn.prepareStatement("UPDATE members SET username =?, address =?, phone =?, gender =?, userid =?, userpassword =? WHERE membernum = ?");
            memberUpdate.setString(1, memberVO.getUsername());
            memberUpdate.setString(2, memberVO.getAddress());
            memberUpdate.setString(3, memberVO.getPhone());
            memberUpdate.setString(4, memberVO.getGender());
            memberUpdate.setString(5, memberVO.getUserid());
            memberUpdate.setString(6, memberVO.getUserpassword());
            memberUpdate.setLong(7, membernum);
            memberUpdate.executeUpdate();



            hobbyInsert = conn.prepareStatement("INSERT INTO memberhobby (membernum, hobbyname) VALUES (?, ?)");
            for (String h : hobby) {
                hobbyInsert.setLong(1, membernum);
                hobbyInsert.setString(2, h);
                hobbyInsert.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try { if (hobbyInsert != null) hobbyInsert.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (memberUpdate != null) memberUpdate.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (hobbyDelete != null) hobbyDelete.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
//                    conn.close();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }


    public void signUp(MemberVO memberVO, String[] str) {
        Connection conn = null;
        PreparedStatement memberInsert = null;
        PreparedStatement hobbyInsert = null;

        try {
            conn = DBConnectionUtil.DATABASE.getConnection();
            conn.setAutoCommit(false);

            String memberInsertSQL = "INSERT INTO members (username, address, phone, gender, userid, userpassword) VALUES (?, ?, ?, ?, ?, ?)";
            memberInsert = conn.prepareStatement(memberInsertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            memberInsert.setString(1, memberVO.getUsername());
            memberInsert.setString(2, memberVO.getAddress());
            memberInsert.setString(3, memberVO.getPhone());
            memberInsert.setString(4, memberVO.getGender());
            memberInsert.setString(5, memberVO.getUserid());
            memberInsert.setString(6, memberVO.getUserpassword());
            memberInsert.executeUpdate();

            ResultSet generatedKeys = memberInsert.getGeneratedKeys();
            if (generatedKeys.next()) {
                long memberNum = generatedKeys.getLong(1);

                String hobbyInsertSQL = "INSERT INTO memberhobby (membernum, hobbyname) VALUES (?, ?)";
                hobbyInsert = conn.prepareStatement(hobbyInsertSQL);

                for (String h : str) {
                    hobbyInsert.setLong(1, memberNum);
                    hobbyInsert.setString(2, h);
                    hobbyInsert.executeUpdate();
                }
            }
            
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }




    public MemberDTO detailMember(String userid){
        MemberDTO memberDTO = null;
        try{
            memberDetail.setString(1, userid);

            ResultSet rs = memberDetail.executeQuery();

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

//    UPDATE members SET uuid = ? WHERE userid = ?
    public void updateUuid(String userid, String uuid) throws Exception{
        try{
            updateUuid.setString(1, uuid);
            updateUuid.setString(2, userid);

            int update = updateUuid.executeUpdate();
            System.out.println("dudu: " + update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
