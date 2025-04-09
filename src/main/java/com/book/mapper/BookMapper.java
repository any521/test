package com.book.mapper;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "bid", column = "bid"),
            @Result(property = "book_name", column = "title"),
            @Result(property = "student_name", column = "name"),
            @Result(property = "time", column = "borrowtime"),
            @Result(property = "sid", column = "sid")
    })
    @Select("select * from borrow ,student ,book where borrow.bid = book.bid and borrow.sid = student.sid")
    List<Borrow> getBorrowList();

    @Delete("delete from  borrow where id = #{id}")
    void deleteBorrow(String id);

    @Select("select * from book")
    List<Book> getAllBooks();

    @Select("select * from student")
    List<Student> getStudentList();


   @Insert("insert into borrow (bid,sid,borrowtime) values (#{bid},#{sid},NOW())")
    void addBorrow(@Param("bid") int bid,@Param("sid") int sid);
}
