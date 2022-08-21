package com.martinez.CQRSSample.CRUD.controller;

import com.martinez.CQRSSample.CRUD.dto.StudentDto;
import com.martinez.CQRSSample.CRUD.entity.Course;
import com.martinez.CQRSSample.CRUD.entity.Enrollment;
import com.martinez.CQRSSample.CRUD.entity.Enrollment.Grade;
import com.martinez.CQRSSample.CRUD.entity.Student;
import com.martinez.CQRSSample.CRUD.repository.CourseRepository;
import com.martinez.CQRSSample.CRUD.repository.StudentRepository;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
@Slf4j
public record StudentController(StudentRepository studentRepository,
                                CourseRepository courseRepository) {

  @GetMapping()
  public List<StudentDto> GetList(String enrolled, int number) {
    return convertToDtos(
        studentRepository.getList(enrolled, number));
  }

  private List<StudentDto> convertToDtos(List<Student> students) {
    //TODO
    return List.of();
  }

  @PostMapping()
  public void create(@RequestBody StudentDto studentDto) throws Exception {
    Student student = new Student(studentDto.getName(), studentDto.getEmail());
    if (studentDto.getCourse1() != null &&
        studentDto.getCourse1Grade() != null) {
      Course course = courseRepository.getByName(studentDto.getCourse1());
      student.enroll(course, Grade.valueOf(studentDto.getCourse1Grade()));
    }
    if (studentDto.getCourse2() != null &&
        studentDto.getCourse2Grade() != null) {
      Course course = courseRepository.getByName(studentDto.getCourse2());
      student.enroll(course, Grade.valueOf(studentDto.getCourse2Grade()));
    }
    studentRepository.save(student);
  }

  @DeleteMapping("{id}")
  public void delete(long id) {
    Student student = studentRepository.getById(id);
    if (student == null) {
      log.error("No student found for Id {id}");
    }
    studentRepository.delete(student);
  }

  @PutMapping("{id}")
  public void update(long id, @RequestBody StudentDto studentDto) throws Exception {
    Student student = studentRepository.getById(id);
    if (student == null) {
      throw new Error("No student found for Id {id}");
    }
    student.setName(studentDto.getName());
    student.setEmail(studentDto.getEmail());
    Enrollment firstEnrollment = student.getEnrollments().get(0);
    Enrollment secondEnrollment = student.getEnrollments().get(1);
    //第一堂選課
    if (hasEnrollmentChange(studentDto.getCourse1(), studentDto.getCourse1Grade(),
        firstEnrollment)) {
      if (studentDto.getCourse1().isEmpty()) {
        //退選
        if (studentDto.getCourse1DisenrollmentComment().isEmpty()) {
          throw new Error("Disenrollment comment is required");
        }
        Enrollment enrollment = firstEnrollment;
        student.removeEnrollment(enrollment);
        student.addDisenrollmentComment(enrollment, studentDto.getCourse1DisenrollmentComment());
      }
      if (studentDto.getCourse1Grade().isEmpty()) {
        throw new Error("Grade is required");
      }
      Course course = courseRepository.getByName(studentDto.getCourse1());
      //新增或修改成績
      if (firstEnrollment == null) {
        student.enroll(course, Grade.valueOf(studentDto.getCourse1Grade()));
      } else {
        firstEnrollment.update(course, Grade.valueOf(studentDto.getCourse1Grade()));
      }
    }

    //第二堂選課
    if (hasEnrollmentChange(studentDto.getCourse2(), studentDto.getCourse2Grade(),
        secondEnrollment)) {
      if (studentDto.getCourse2().isEmpty()) {
        //退選
        if (studentDto.getCourse2DisenrollmentComment().isEmpty()) {
          throw new Error("Disenrollment comment is required");
        }
        Enrollment enrollment = secondEnrollment;
        student.removeEnrollment(enrollment);
        student.addDisenrollmentComment(enrollment, studentDto.getCourse2DisenrollmentComment());
      }
      if (studentDto.getCourse2Grade().isEmpty()) {
        throw new Error("Grade is required");
      }
      Course course = courseRepository.getByName(studentDto.getCourse2());
      //新增或修改成績
      if (firstEnrollment == null) {
        student.enroll(course, Grade.valueOf(studentDto.getCourse2()));
      } else {
        firstEnrollment.update(course, Grade.valueOf(studentDto.getCourse2()));
      }

    }
  }

  private boolean hasEnrollmentChange(String newCourseName, String newGrade,
      Enrollment enrollment) {
    if (newCourseName.isEmpty() && enrollment == null) {
      return false;
    }
    if (newCourseName.isEmpty() || enrollment == null) {
      return true;
    }
    return !newCourseName.equals(enrollment.getCourse().getName())
        || !Objects.equals(newGrade, enrollment.getGrade().toString());
  }

}
