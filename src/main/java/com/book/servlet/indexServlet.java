package com.book.servlet;

import com.book.entity.User;
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

@WebServlet("/index")
public class indexServlet extends HttpServlet {

    Bookservice bookservice;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bookservice = new Bookserviceimpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());
        context.setVariable("borrow_list", bookservice.getBorrowList());
        context.setVariable("book_count",bookservice.getBookList().size());
        context.setVariable("student_count",bookservice.getStudentList().size());
        ThymeleafUtil.process("index.html",context,resp.getWriter());
    }

}
