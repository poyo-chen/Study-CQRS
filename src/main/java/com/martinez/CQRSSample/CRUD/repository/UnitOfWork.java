package com.martinez.CQRSSample.CRUD.repository;

import com.martinez.CQRSSample.CRUD.entity.Student;
import java.util.List;

public class UnitOfWork {

  public List<Student> query() {
    return null;
  }

  public Student saveOrUpdate(Student student) {
    return new Student();
  }

  public Student get(long id) {
    return new Student();
  }

  public void delete(Student student) {
  }
}
