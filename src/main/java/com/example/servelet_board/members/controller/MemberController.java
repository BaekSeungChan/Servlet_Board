package com.example.servelet_board.members.controller;

import com.example.servelet_board.members.dao.MemberDAO;
import com.example.servelet_board.members.dao.MemberVO;
import com.example.servelet_board.members.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class MemberController {

    MemberDAO memberDAO = new MemberDAO();

    public String myPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userid = (String) session.getAttribute("userid");


        MemberDTO memberDTO = memberDAO.detailMember(userid);

        req.setAttribute("member", memberDTO);

        return "MyPage";
    }


    public String adminDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String jspPage = null;
        int membernum = Integer.parseInt(req.getParameter("membernum"));

        System.out.println("ddd " + membernum);
        System.out.println("ccjcjj");

        memberDAO.adminDelete(membernum);

        jspPage = "redirect:/member?action=adminPage";

        return jspPage;
    }

    public String updateProfile(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String jspPage = null;

        int membernum = Integer.parseInt(req.getParameter("membernum"));
        String userid = req.getParameter("userid");
        String password1 = req.getParameter("userpassword1");
        String password2 = req.getParameter("userpassword2");
        String username = req.getParameter("username");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");


        MemberVO memberVO = MemberVO.builder()
                .userid(userid)
                .userpassword(password1)
                .username(username)
                .address(address)
                .phone(phone)
                .gender(gender)
                .build();

        String[] hobbies = req.getParameterValues("hobby");

        if (password2 != null && password2.equals(password1)) {
            memberDAO.updateProfile(memberVO, hobbies, membernum);
            jspPage = "redirect:/board.do?action=main";
        } else {
            req.getRequestDispatcher("/WEB-INF/views/members/LoginError.jsp").forward(req, res);
        }

        return jspPage;
    }

    public String signUpPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "SignUpPage";
    }

    public String LoginPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        return "LoginPage";
    }

    public String adminPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<MemberDTO> memberDTO = memberDAO.selectAll();

        for(var a : memberDTO){
            System.out.println(a);
        }

        req.setAttribute("admin", memberDTO);

        req.getRequestDispatcher("/WEB-INF/views/admin/adminPage.jsp").forward(req,res);
        return "adminPage";
    }

    public String signOut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String jspPage = null;

        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate();


        jspPage = "redirect:/board.do?action=main";

        return jspPage;
    }


    public String login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userid = req.getParameter("userid");
        String userpassword = req.getParameter("userpassword");
        String auto = req.getParameter("autoLogin");

        boolean rememberMe = auto != null && auto.equals("on");


        try {
            MemberVO memberVO = memberDAO.LoginCheck(userid);


            if (rememberMe && memberVO != null) {
                String uuid = UUID.randomUUID().toString();
                memberDAO.updateUuid(userid, uuid);
                memberVO.setUuid(uuid);

                Cookie remberCookie = new Cookie("remember-me" , uuid);
                remberCookie.setMaxAge(60*60*24*7);
                remberCookie.setPath("/");
                res.addCookie(remberCookie);
            }

            if (memberVO != null && userpassword.equals(memberVO.getUserpassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("userid", memberVO.getUserid());

                if (userid.equals("Qortmdcks95") && userpassword.equals("1234")) {
                    return "redirect:/member?action=adminPage";
                } else {
                    return "redirect:/board.do?action=main";
                }
            } else {
                req.setAttribute("errorMessage", "사용자 이름 또는 비밀번호가 잘못되었습니다.");
                return "LoginPage";
            }
        } catch (Exception e) {
            // 예외 처리 코드 추가
            return "LoginPage";
        }
    }



    public String membersignUp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String jspPage = null;

        String userid = req.getParameter("userid");
        String password1 = req.getParameter("userpassword1");
        String password2 = req.getParameter("userpassword2");
        String username = req.getParameter("username");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String gender = req.getParameter("gender");

        MemberVO memberVO = MemberVO.builder()
                .userid(userid)
                .userpassword(password1)
                .username(username)
                .address(address)
                .phone(phone)
                .gender(gender)
                .build();

        String[] hobbies = req.getParameterValues("hobby");

        if (password2 != null && password2.equals(password1)) {
            memberDAO.insert(memberVO, hobbies);
            jspPage = "redirect:/board.do?action=list";
        } else {
            req.getRequestDispatcher("/WEB-INF/views/members/LoginError.jsp").forward(req, res);
        }

        return jspPage;
    }
}
