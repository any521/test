package com.book.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlUtil {
    public static final SqlSessionFactory factory;
    static {
        try {
            factory =new  SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static SqlSession getSession(){
        return factory.openSession(true);
    }
}
