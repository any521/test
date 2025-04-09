package com.book.servlet;
import com.book.serice.Userserice;
import com.book.serice.impl.UserServiceimpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    Userserice userserice ;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userserice = new UserServiceimpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if(req.getSession().getAttribute("error") != null){
            context.setVariable("failure",true);
            req.getSession().removeAttribute("error");
        }
        if(req.getSession().getAttribute("user") != null){
            resp.sendRedirect("index");
            return ;
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        if(userserice.jiance(username,password,req.getSession())){
            resp.sendRedirect("index");
        }else {
            req.getSession().setAttribute("error",new Object());
            this.doGet(req,resp);
        }
    }
}
