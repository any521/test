package com.book.manage;

import com.book.serice.Bookservice;
import com.book.serice.impl.Bookserviceimpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/add-borrow")
public class addBorrowServlet extends HttpServlet {
    Bookservice bookservice;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bookservice = new Bookserviceimpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("book_list",bookservice.getActiveBookList());
        context.setVariable("student_list",bookservice.getStudentList());
        ThymeleafUtil.process("add-borrow.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("student"));
        int bid = Integer.parseInt(req.getParameter("book"));
        bookservice.addBorrow(sid,bid);
        resp.sendRedirect("index");
    }
}
