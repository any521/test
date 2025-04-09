package com.book.mapper;

import com.book.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MainMapper {

    @Select("select * from admin where username=#{username} and password=#{password}")
    public User getUser(@Param("username") String username,@Param("password") String password);
}
