package com.book.mapper;

import com.book.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {

    @Select("select * from student")
    List<Student> getAllStudents();
}
