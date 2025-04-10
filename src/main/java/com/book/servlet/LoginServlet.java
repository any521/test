package com.book.servlet;
import com.book.serice.Userserice;
import com.book.serice.impl.UserServiceimpl;
import com.book.utils.ThymeleafUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
        Cookie[] cookies = req.getCookies();
        String username = null;
        String password = null;
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("username")){
                    username = cookie.getValue();
                }
                if(cookie.getName().equals("password")){
                    password = cookie.getValue();
                }
            }
            if(username != null && password != null){
                if(userserice.jiance(username,password,req.getSession())){
                    resp.sendRedirect("index");
                    return ;
                }
            }
        }

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
            if(remember != null){
                Cookie cookie_username = new Cookie("username",username);
                cookie_username.setMaxAge(60*60*24*7);
                Cookie cookie_password = new Cookie("password",password);
                cookie_password.setMaxAge(60*60*24*7);
                resp.addCookie(cookie_username);
                resp.addCookie(cookie_password);
                resp.sendRedirect("index");
            }
        }else {
            req.getSession().setAttribute("error",new Object());
            this.doGet(req,resp);
        }
    }
}
