package com.book.serice.impl;

import com.book.entity.User;
import com.book.mapper.MainMapper;
import com.book.serice.Userserice;
import com.book.utils.SqlUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class UserServiceimpl implements Userserice {
    @Override
    public Boolean jiance(String username, String password, HttpSession session) {
       try(SqlSession session1 = SqlUtil.getSession()){
           MainMapper mapper = session1.getMapper(MainMapper.class);
           User user = mapper.getUser(username,password);
           if(user != null){
               session.setAttribute("user",user);
           }else{
               return false;
           }
       }
       return true;
    }
}
