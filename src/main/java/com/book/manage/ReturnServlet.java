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


@WebServlet("/return-book")
public class ReturnServlet extends HttpServlet {

    Bookservice bookservice;

    @Override
    public void init(ServletConfig config) throws ServletException {
        bookservice = new Bookserviceimpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookservice.returnBook(id);
        resp.sendRedirect("index");
    }
}
