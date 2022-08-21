package com.martinez.CQRSSample.CRUD.repository;

import com.martinez.CQRSSample.CRUD.entity.Course;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public record CourseRepository(UnitOfWork unitOfWork) {

  public Course getByName(String name) {
//    return unitOfWork.query().stream().filter(student -> student.getName().equals(name));
    return null;
  }
}
