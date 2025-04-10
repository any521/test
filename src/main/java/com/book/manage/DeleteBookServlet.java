package com.book.manage;

import com.book.serice.Bookservice;
import com.book.serice.impl.Bookserviceimpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-book")
public class DeleteBookServlet extends HttpServlet{

    Bookservice bookservice;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bookservice = new Bookserviceimpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bid = Integer.parseInt(req.getParameter("bid"));
        bookservice.deleteBook(bid);
        resp.sendRedirect("books");
    }
}
