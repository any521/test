package com.book.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Borrow{
    int id;
    int bid;
    String book_name;
    Date time;
    String student_name;
    int sid;
}
