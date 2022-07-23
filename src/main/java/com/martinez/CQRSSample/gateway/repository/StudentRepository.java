package com.martinez.CQRSSample.gateway.repository;

import com.martinez.CQRSSample.gateway.entity.Student;
import java.util.List;

public class StudentRepository {

  public List<Student> getList(String enrolled, int number) {
    return List.of();
  }

  public void save(Student student) {
  }

  public Student getById(long id) {
    return new Student();
  }

  public void delete(Student student) {
  }
}
