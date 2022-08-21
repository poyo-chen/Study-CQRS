package com.martinez.CQRSSample.CRUD.repository;

import com.martinez.CQRSSample.CRUD.entity.Student;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public record StudentRepository(UnitOfWork unitOfWork) {


  public List<Student> getList(String enrolledIn, int numberOfCourse) {
    List<Student> students = unitOfWork.query();
    if (!enrolledIn.isEmpty()) {
      students.stream().filter(
          student ->
              !(student.getEnrollments().stream().filter(
                      enrollment -> Objects.equals(enrollment.getCourse().getName(), enrolledIn))
                  .count() == 0)).collect(
          Collectors.toList());
    }
    students.stream().filter(student -> student.getEnrollments().size() == numberOfCourse).collect(
        Collectors.toList());
    return students;
  }

  public Student save(Student student) {
    return unitOfWork.saveOrUpdate(student);
  }

  public Student getById(long id) {
    return unitOfWork.get(id);
  }

  public void delete(Student student) {
    unitOfWork.delete(student);
  }
}
