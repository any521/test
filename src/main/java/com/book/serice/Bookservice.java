package com.book.serice;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;

import java.util.List;

public interface Bookservice {
    List<Borrow> getBorrowList();
    void returnBook(String id);
    List<Book> getActiveBookList();
    List<Student> getStudentList();
    void addBorrow(int sid,int bid);
}
