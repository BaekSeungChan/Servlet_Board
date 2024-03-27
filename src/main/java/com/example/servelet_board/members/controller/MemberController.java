package com.example.servelet_board.members.controller;

import com.example.servelet_board.members.dao.MemberDAO;
import com.example.servelet_board.members.dao.MemberVO;
import com.example.servelet_board.members.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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


    public String Login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String jspPage = null;

        String userid = req.getParameter("userid");
        String userpassword = req.getParameter("userpassword");


        MemberVO memberVO = memberDAO.LoginCheck(userid);

        if (memberVO == null || !userpassword.equals(memberVO.getUserpassword())) {
            req.setAttribute("errorMessage", "사용자 이름 또는 비밀번호가 잘못되었습니다.");
            jspPage = "LoginPage";
        } else if (userid.equals("Qortmdcks95") && userpassword.equals("1234")) {
            res.sendRedirect("/member?action=adminPage");
        }
        else {
            HttpSession session = req.getSession();
            session.setAttribute("userid", memberVO.getUserid());
            jspPage = "redirect:/board.do?action=main";
        }

        return jspPage;
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
