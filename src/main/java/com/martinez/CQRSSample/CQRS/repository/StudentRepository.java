package com.martinez.CQRSSample.CQRS.repository;

import com.martinez.CQRSSample.CQRS.dto.StudentDto;
import com.martinez.CQRSSample.CRUD.entity.Student;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentRepository implements Repository {

  public Student getById(long id) {
    return null;
  }

  public void update(Student student) {
  }

  public List<StudentDto> getList(String enrolledIn, int numberOfCourses) {
    return null;
  }
}
