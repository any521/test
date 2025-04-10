package com.book.pages;

import com.book.entity.Book;
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
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

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
        Map<Book, Boolean> map = bookservice.getBookList();
        context.setVariable("book_list",map.keySet());
        context.setVariable("book_list_status",new ArrayList<>(map.values()));
        System.out.println("book_list_status: " + map.values());
// 打印更多信息以帮助调试...
        ThymeleafUtil.process("books.html", context, resp.getWriter());
    }
}
