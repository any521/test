package com.book.serice.impl;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.mapper.BookMapper;
import com.book.serice.Bookservice;
import com.book.utils.SqlUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Bookserviceimpl implements Bookservice {

    @Override
    public List<Borrow> getBorrowList() {
        try(SqlSession sqlSession = SqlUtil.getSession()){
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            return bookMapper.getBorrowList();
        }
    }

    @Override
    public void returnBook(String id) {
        try(SqlSession sqlSession = SqlUtil.getSession()){
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            bookMapper.deleteBorrow(id);
        }
    }

    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow ->set.add(borrow.getBid()));
        try(SqlSession sqlSession = SqlUtil.getSession()){
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            return  bookMapper.getAllBooks()
                    .stream()
                    .filter(book->!set.contains(book.getBid()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<Student> getStudentList() {
        try(SqlSession sqlSession = SqlUtil.getSession()){
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            return bookMapper.getStudentList();
        }
    }

    @Override
    public void addBorrow(int sid, int bid) {
        try(SqlSession sqlSession = SqlUtil.getSession()){
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            bookMapper.addBorrow(bid,sid);
        }
    }
}
