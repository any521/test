package com.book.servlet;

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

@WebServlet("/add-book")
public class addBookServlet extends HttpServlet {
    Bookservice bookservice;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bookservice = new Bookserviceimpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ThymeleafUtil.process("add-book.html",new Context(),resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        double price = Double.parseDouble(req.getParameter("price"));
        bookservice.addBook(title,desc,price);
        resp.sendRedirect("books");
    }
}
